cmake_minimum_required(VERSION 3.23)
project(SecondLab)

set(CMAKE_CXX_STANDARD 14)

add_executable(SecondLab XML.cpp Attribute.cpp Attribute.h Comment.cpp Comment.h Document.cpp Document.h Element.cpp Element.h ProcessingInstruction.cpp ProcessingInstruction.h TagElement.cpp TagElement.h TextElement.cpp TextElement.h XmlPrinter.cpp XmlPrinter.h XmlReader.cpp XmlReader.h)
