#!/usr/bin/env python
import numpy as np

def arsinh(x):
    ''' Logarithmic activation function (hyperbolic arcsine) '''
    return np.arcsinh(x)


def darsinh(x):
    ''' Derivative of the logarithmic activation function '''
    return 1.0 / np.sqrt(1 + x ** 2)


# 1 ряд фиббоначи
def fibonacci(n):
    if n == 0:
        return 0
    elif n == 1:
        return 1
    else:
        return fibonacci(n - 1) + fibonacci(n - 2)


# 2 периодическая функция  f(x) = (-1)^x
def periodic(n):
    return (-1) ** n


# 3 степенная функция f(x) = x ^2
def step(n):
    return n ** 2


class Jordan:
    ''' Jordan network '''

    def __init__(self, *args):
        ''' Initialization of the perceptron with given sizes.  '''
        self.scale_k = 100 # нужно как-то менять h*n*out 100
        self.shape = args
        n = len(args)

        # Build layers
        self.layers = []
        # Input layer (+1 unit for bias
        #              +size of oputput layer)
        self.layers.append(np.ones(self.shape[0] + 1 + self.shape[-1]))
        # Hidden layer(s) + output layer
        for i in range(1, n):
            self.layers.append(np.ones(self.shape[i]))
        # Build weights matrix (randomly between -0.25 and +0.25)
        self.weights = []
        for i in range(n - 1):
            self.weights.append(np.zeros((self.layers[i].size,
                                          self.layers[i + 1].size)))

        # dw will hold last change in weights (for momentum)
        self.dw = [0, ] * len(self.weights)

        # Reset weights
        self.reset()

    def reset(self):
        ''' Reset weights '''
        for i in range(len(self.weights)):
            Z = np.random.random((self.layers[i].size, self.layers[i + 1].size))
            self.weights[i][...] = (2 * Z - 1)

    def propagate_forward(self, data):
        ''' Propagate data from input layer to output layer. '''
        data = data / self.scale_k
        # Set input layer with data
        self.layers[0][0:self.shape[0]] = data
        # and output layer
        self.layers[0][self.shape[0]:-1] = self.layers[-1]

        # Propagate from layer 0 to layer n-1 using arsinh as activation function
        for i in range(1, len(self.shape)):
            # Propagate activity
            self.layers[i][...] = arsinh(np.dot(self.layers[i - 1], self.weights[i - 1]))

        # Return output
        return self.layers[-1] * self.scale_k

    def propagate_backward(self, target, lrate=0.1, momentum=0.1):
        ''' Back propagate error related to target using lrate. '''
        target = target / self.scale_k
        deltas = []

        # Compute error on output layer
        error = target - self.layers[-1]
        delta = error * darsinh(self.layers[-1])
        deltas.append(delta)

        # Compute error on hidden layers
        for i in range(len(self.shape) - 2, 0, -1):
            delta = np.dot(deltas[0], self.weights[i].T) * darsinh(self.layers[i])
            deltas.insert(0, delta)

        # Update weights
        for i in range(len(self.weights)):
            layer = np.atleast_2d(self.layers[i])
            delta = np.atleast_2d(deltas[i])
            dw = np.dot(layer.T, delta)
            self.weights[i] += lrate * dw + momentum * self.dw[i]
            self.dw[i] = dw

        # Return error
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

    print('1) fibonacci (recommended error = 0.0000001)')
    print('2) periodic (recommended error = 0.0000001)')
    print('3) power (recommended error = 0.0000001)')
    print('input the number of desired sequence')
    sequence = input()
    desired_error = float(input("input error\n"))

    input_to_function = {
      '1': fibonacci,
      '2': periodic,
      '3': step
    }

    input_m = generate_train_matrix(input_to_function[sequence], (h, n))
    output = generate_train_matrix_result(input_to_function[sequence], (h, n, out))

    current_error = 1.0
    while current_error > desired_error:
        for i in range(n):
            network.propagate_forward(input_m[i])
            current_error = network.propagate_backward(output[i])
            #print("error ",current_error )


    for i in range(len(input_m)):
        o = network.propagate_forward(input_m[i])
        print('Sample %d: %s -> %s' % (i, input_m[i], output[i]))
        print('               Network output: " ', o)
        print()
