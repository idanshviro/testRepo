package com.company;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class BSTNode {

    private LocalDate dateOfBirth;
    private String name;
    private int id;
    private BSTNode left,right;
    public static int counter = 0;
    public static ArrayList<BSTNode> nodesList = new ArrayList<BSTNode>();

    public BSTNode(LocalDate date, String name) {
        this.dateOfBirth = date;
        this.name = name;
        counter++;
        this.id = counter;
    };

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public BSTNode getLeft() {
        return left;
    }

    public void setLeft(BSTNode left) {
        this.left = left;
    }

    public BSTNode getRight() {
        return right;
    }

    public void setRight(BSTNode right) {
        this.right = right;
    }

    public int addNode(BSTNode newNode) {
        if (newNode.dateOfBirth.isAfter(this.dateOfBirth)) {
            if (left == null) {
                left = newNode;
            } else
                return left.addNode(newNode);
        } else if (newNode.dateOfBirth.isBefore(this.dateOfBirth)) {
            if (right == null) {
                right = newNode;
            } else
                return right.addNode(newNode);
        }
        return counter;
    }

    public void searchNode(LocalDate searchDate) {

        if (searchDate.isEqual(this.dateOfBirth))
            printNameAndId();
        else if (searchDate.isAfter(this.dateOfBirth)) {
            if (left == null)
                System.out.println(" node not found");
            else
                left.searchNode(searchDate);
        } else if (searchDate.isBefore(this.dateOfBirth)) {
            if (right == null)
                System.out.println(" node not found");
            else
                right.searchNode(searchDate);
        }

    }


    public void removeNode(LocalDate removeNodeDate, BSTNode parent) {
        if (removeNodeDate.isAfter(this.dateOfBirth)) {
            if (left != null)
                left.removeNode(removeNodeDate, this);
            else
                System.out.println("node not found");
        } else if (removeNodeDate.isBefore(this.dateOfBirth)) {
            if (right != null)
                right.removeNode(removeNodeDate, this);
            else
                System.out.println("node not found");
        } else {
            if (left != null && right != null) {
                this.dateOfBirth = right.minValue().dateOfBirth;
                this.name = right.minValue().name;
                this.id = right.minValue().id;

                right.removeNode(this.dateOfBirth, this);
            } else if (parent.left.dateOfBirth.isEqual(this.dateOfBirth)) {
                parent.left = (left != null) ? left : right;
                nodesList.remove(this);
            } else if (parent.right == this) {
                parent.right = (left != null) ? left : right;
                nodesList.remove(this);

            }
        }
    }

    private BSTNode minValue() {
        if (left == null)
            return this;
        else
            return left.minValue();
    }

    public void printAll(){
        for(BSTNode node : nodesList)
            node.print();
    }

    public void print(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMM");
        System.out.println(id + " : "+  this.dateOfBirth.format(formatter) + " : " + this.name);
    }

    public void printNameAndId() {System.out.println("id " + this.id + ": " + this.name);}

}