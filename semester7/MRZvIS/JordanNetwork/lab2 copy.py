import numpy as np

def arsinh(x):
    return np.arcsinh(x)


def darsinh(x):
    return 1.0 / np.sqrt(1 + x ** 2)

def fibonacci(n):
    if n == 0:
        return 0
    elif n == 1:
        return 1
    else:
        return fibonacci(n - 1) + fibonacci(n - 2)


class Jordan:
    def __init__(self, *args):
        self.scale_k = 100
        self.shape = args
        n = len(args)

        self.layers = []

        self.layers.append(np.ones(self.shape[0] + 1 + self.shape[-1]))
        for i in range(1, n):
            self.layers.append(np.ones(self.shape[i]))
        self.weights = []
        for i in range(n - 1):
            self.weights.append(np.zeros((self.layers[i].size,
                                          self.layers[i + 1].size)))

        self.dw = [0, ] * len(self.weights)

        self.reset()

    def reset(self):
        for i in range(len(self.weights)):
            Z = np.random.random((self.layers[i].size, self.layers[i + 1].size))
            self.weights[i][...] = 2 * Z - 1

    def propagate_forward(self, data):
        data = data / self.scale_k
        self.layers[0][0:self.shape[0]] = data
        self.layers[0][self.shape[0]:-1] = self.layers[-1]

        for i in range(1, len(self.shape)):
            self.layers[i][...] = arsinh(np.dot(self.layers[i - 1], self.weights[i - 1]))

        return self.layers[-1] * self.scale_k

    def propagate_backward(self, target, lrate=0.1, momentum=0.1):
        target = target / self.scale_k
        deltas = []

        error = target - self.layers[-1]
        delta = error * darsinh(self.layers[-1])
        deltas.append(delta)

        for i in range(len(self.shape) - 2, 0, -1):
            delta = np.dot(deltas[0], self.weights[i].T) * darsinh(self.layers[i])
            deltas.insert(0, delta)

        for i in range(len(self.weights)):
            layer = np.atleast_2d(self.layers[i])
            delta = np.atleast_2d(deltas[i])
            dw = np.dot(layer.T, delta)
            self.weights[i] += lrate * dw + momentum * self.dw[i]
            self.dw[i] = dw

        return (error ** 2).sum()

def generate_train_matrix(sequence_function, shape):
    train_matrix = np.zeros(shape)
    for i in range(shape[0]):
        for j in range(shape[1]):
            train_matrix[i][j] = sequence_function(j + i)
    return train_matrix


def generate_train_matrix_result(sequence_function, shape):
    result_matrix = np.zeros((shape[0],shape[2]))
    for i in range(shape[0]):
        for j in range(shape[2]):
            result_matrix[i][j] = sequence_function(j + i + shape[1])
    return result_matrix

# -----------------------------------------------------------------------------
if __name__ == '__main__':
    n = 4
    h = 6
    out = 1
    network = Jordan(n, h, out)

    desired_error = float(0.000001)

    input_m = generate_train_matrix(fibonacci, (h, n))
    output = generate_train_matrix_result(fibonacci, (h, n, out))

    current_error = 1.0
    while current_error > desired_error:
        for i in range(n):
            network.propagate_forward(input_m[i])
            current_error = network.propagate_backward(output[i])
            print("error ",current_error )

    for i in range(len(input_m)):
        o = network.propagate_forward(input_m[i])
        print('Sample %d: %s -> %s' % (i, input_m[i], output[i]))
        print('               Network output: " ', o)
        print()
