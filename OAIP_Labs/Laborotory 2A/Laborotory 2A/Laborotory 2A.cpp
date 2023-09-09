#include <iostream>
#include <SFML/Graphics.hpp>
#include <math.h>

using namespace sf;

int main()
{
    RenderWindow window(sf::VideoMode(800, 600), "Picture");
    window.setVerticalSyncEnabled(true);
    VertexArray va(LinesStrip, rand() % 10 + 2);
    Vector2u window_size = window.getSize();
    for (size_t i = 0, count = va.getVertexCount(); i < count; ++i) {
        va[i].position.x = rand() % window_size.x;
        va[i].position.y = rand() % window_size.y;
        va[i].color = Color(rand() % 200, rand() % 200, rand() % 200);
    }
    Event event;
    while (window.isOpen()) {
        while (window.pollEvent(event)) {
            if (event.type == sf::Event::Closed) {
                window.close();
            }
        }
        window.clear(sf::Color::Black);
        window.draw(va);
        window.display();
    }
}