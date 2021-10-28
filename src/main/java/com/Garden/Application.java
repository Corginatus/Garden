package com.Garden;

import com.Garden.entity.Root;
import com.Garden.entity.Tree;
import com.Garden.parsing.SaxMyParser;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

public class Application {
    public static void main(String[] args) {
        String fileName = "final.xml";

        SaxMyParser parser = new SaxMyParser();
        Root root = parser.parse();

        toPlantAGarden(root);
    }

    static void toPlantAGarden(Root root) {
        final int TAG_MAX_RANDOM = 5;
        System.out.println("Посадка сада - Start");

        int totalHeight = 0;
        int totalNum = 0;

        Random random = new Random();
        int rand = 0;

        // StAX
        try {
            XMLOutputFactory output = XMLOutputFactory.newInstance();
            XMLStreamWriter writer = output.createXMLStreamWriter(new FileWriter("final.xml"));

            // Открываем XML-документ и Пишем корневой элемент BookCatalogue
            writer.writeStartDocument("1.0");
            writer.writeStartElement("BookCatalogue");
            int id = 1;
            for (Tree tree : root.getTree()) {

                tree.printPlantName();
                rand = random.nextInt(TAG_MAX_RANDOM);
                System.out.println(" count: " + ++rand);
                totalHeight+=(tree.getHeight()*rand);
                totalNum+=rand;

                // Записываем Tree
                writer.writeStartElement("Tree");
                // Заполняем все тэги для Tree
                // ID
                writer.writeStartElement("ID");
                writer.writeCharacters("#" + id++);
                writer.writeEndElement();
                // Height
                writer.writeStartElement("Height");
                writer.writeCharacters("" + tree.getHeight());
                writer.writeEndElement();
                // Name
                writer.writeStartElement("Name");
                writer.writeCharacters("" + tree.getName());
                writer.writeEndElement();
                // Count
                writer.writeStartElement("Count");
                writer.writeCharacters("" + rand);
                writer.writeEndElement();
                // Закрываем тэг Tree
                writer.writeEndElement();
            }
            for (Tree bush : root.getBush()) {

                bush.printPlantName();
                rand = random.nextInt(TAG_MAX_RANDOM);
                System.out.println(" count: " + ++rand);
                totalHeight+=(bush.getHeight()*rand);
                totalNum+=rand;

                // Записываем Bush
                writer.writeStartElement("Bush");
                // Заполняем все тэги для Bush
                // ID
                writer.writeStartElement("ID");
                writer.writeCharacters("#" + id++);
                writer.writeEndElement();
                // Height
                writer.writeStartElement("Height");
                writer.writeCharacters("" + bush.getHeight());
                writer.writeEndElement();
                // Name
                writer.writeStartElement("Name");
                writer.writeCharacters("" + bush.getName());
                writer.writeEndElement();
                // Count
                writer.writeStartElement("Count");
                writer.writeCharacters("" + rand);
                writer.writeEndElement();
                // Закрываем тэг Bush
                writer.writeEndElement();
            }
            // Закрываем корневой элемент
            writer.writeEndElement();

            writer.writeStartElement("totalHeight");
            writer.writeCharacters("#" + totalHeight);
            writer.writeEndElement();
            writer.writeStartElement("totalNum");
            writer.writeCharacters("#" + totalNum);
            writer.writeEndElement();

            System.out.println("totalHeight: " + totalHeight);
            System.out.println("totalNum: " + totalNum);

            // Закрываем XML-документ
            writer.writeEndDocument();
            writer.flush();
        } catch (XMLStreamException | IOException ex) {
            ex.printStackTrace();
        }

        System.out.println("Посадка сада - End");
    }

}
