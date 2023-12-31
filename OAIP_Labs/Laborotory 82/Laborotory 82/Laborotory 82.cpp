#include <iostream>
#include <SFML/Graphics.hpp>

using namespace std;
using namespace sf;

int main()
{
    setlocale(LC_ALL, "RUSSIA");
    RenderWindow window(VideoMode(1150, 700), "Картинка!");
    window.setFramerateLimit(60);

    Texture pic;
    pic.loadFromFile("Hero.png");
    Sprite hero(pic);

    Texture fone;
    fone.loadFromFile("Dust.png");
    Sprite fon(fone);

    hero.setPosition(100, 445);
    hero.setTextureRect(IntRect(10, 40, 160, 255));
    fon.setPosition(0, 0);

    while (window.isOpen())
    {
        Event event;
        while (window.pollEvent(event))
        {
            if (event.type == Event::Closed)
            {
                window.close();
            }
        }

        if (Keyboard::isKeyPressed(Keyboard::Left))
        {
            hero.move(-15, 0);
        }
        if (Keyboard::isKeyPressed(Keyboard::Right))
        {
            hero.move(15, 0);
        }
        if (Keyboard::isKeyPressed(Keyboard::Up))
        {
            hero.move(0, -15);
        }
        if (Keyboard::isKeyPressed(Keyboard::Down))
        {
            hero.move(0, 15);
        }

        window.clear();
        window.draw(fon);
        window.draw(hero);
        window.display();
    }
    return 0;
}