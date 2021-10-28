package com.Garden.parsing;


import com.Garden.entity.Bush;
import com.Garden.entity.Root;
import com.Garden.entity.Tree;
import com.Garden.repository.Plants;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

public class SaxParserHandler extends DefaultHandler {

    private static final String TAG_NAME_MAIN = "name";
    private static final String TAG_PLANTS = "plants";
    private static final String TAG_ELEMENT = "element";
    private static final String TAG_NAME = "name";
    private static final String TAG_HEIGHT = "height";
    private static final String TAG_TREE = "tree";
    private static final String TAG_BUSH = "bush";

    private Root root = new Root();
    private List<Tree> treeList = new ArrayList<>();
    private List<Bush> bushList = new ArrayList<>();
    private String currentTagName;

    private boolean isPlant = false;
    private boolean isTree = false;
    private boolean isBush = false;
    private boolean isElement = false;

    private String name;
    private int height;

    public Root getRoot() {
        return root;
    }

    @Override
    public void startDocument() throws SAXException {
    }

    @Override
    public void endDocument() throws SAXException {
        root.setTree(treeList);
        root.setBush(bushList);
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        currentTagName = qName;
        switch (currentTagName) {
            case TAG_PLANTS -> {
                isPlant = true;
                break;
            }
            case TAG_ELEMENT -> {
                isElement = true;
                break;
            }
            case TAG_TREE -> {
                isTree = true;
                break;
            }
            case TAG_BUSH -> {
                isBush = true;
                break;
            }
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        currentTagName = qName;
        switch (currentTagName) {
            case TAG_PLANTS -> {
                isPlant = false;
                break;
            }
            case TAG_TREE -> {
                isTree = false;
                break;
            }
            case TAG_BUSH -> {
                isBush = false;
                break;
            }
            case TAG_ELEMENT -> {
                isElement = false;
                if (isTree) {
                    com.Garden.entity.Tree tree = new com.Garden.entity.Tree(height, name);
                    treeList.add(tree);
                } else if (isBush) {
                    Bush bush = new Bush(height, name);
                    bushList.add(bush);
                }
            }
        }
        currentTagName = null;
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {

        if(currentTagName == null) {
            return;
        }

        if (!isPlant && currentTagName.equals(TAG_NAME_MAIN)) {
            root.setName(new String(ch, start, length));
        }

        if (isPlant && isElement) {
            if(currentTagName.equals(TAG_NAME)) {
                name = new  String(ch, start, length);
            } else if (currentTagName.equals(TAG_HEIGHT)) {
                height = Integer.valueOf(new  String(ch, start, length));
            }
        }
    }
}

