package com.yvonne.advanced.design.pattern.structure;

import java.util.Enumeration;
import java.util.Vector;

/**
 * 组合模式：
 *     组合模式有时又叫部分-整体模式，在处理类似树形结构的问题时比较方便
 *     使用场景：将多个对象组合在一起进行操作，常用于表示树形结构中，例如二叉树
 */
public class Composite {
    public static void main(String[] args) {
        //设置树干
        TreeNode treeNode = new TreeNode("A");
        treeNode.printTree();
        System.out.println();

        //设置枝干
        TreeNode treeNode1 = new TreeNode("A11");
        TreeNode treeNode2 = new TreeNode("A22");
        TreeNode treeNode3 = new TreeNode("A33");
        TreeNode treeNode4 = new TreeNode("A44");
        TreeNode treeNode5 = new TreeNode("A55");

        treeNode1.add(treeNode3);
        treeNode1.printTree();
        System.out.println();

        treeNode1.add(treeNode4);
        treeNode1.printTree();
        System.out.println();

        treeNode2.add(treeNode5);
        treeNode2.printTree();
        System.out.println();

        treeNode.add(treeNode1);
        treeNode.printTree();
        System.out.println();

        treeNode.add(treeNode2);
        treeNode.printTree();
        System.out.println();

        treeNode.remove(treeNode1);
        treeNode.printTree();
    }
}

//枝干
class TreeNode{
    private String name;
    //树干的节点有多个
    private Vector<TreeNode> children = new Vector<TreeNode>();
    private int grade = 0;

    public TreeNode(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }
    
    //递归-枝干等级+1
    public void addGrade(){
        this.grade++;
        Enumeration<TreeNode> en = getChildren();
        while (en.hasMoreElements()){
            TreeNode tn = en.nextElement();
            tn.addGrade();
        }
    }

    //取得此枝干内所有节点
    private Enumeration<TreeNode> getChildren() {
        return children.elements();
    }

    //添加新枝干到此枝干的节点
    public void add(TreeNode node){
        node.setGrade(grade); //将节点上的等级设置与自己一样
        node.addGrade();    //节点内的枝干与节点递归遍历等级+1
        children.add(node);
    }

    //指定删除此枝干的某个节点，节点的子节点删除不了
    public void remove(TreeNode node){
        children.remove(node);
    }

    //打印枝干与节点的树状图，树干等级=0，树枝越长等级越高
    public void printTree(){
        for (int i = 0; i < grade; i++) {
//            System.out.println(" ");
        }
        System.out.println("|---" + name);
        Enumeration<TreeNode> children = getChildren();
        while (children.hasMoreElements()){
            TreeNode treeNode = children.nextElement();
            treeNode.printTree();
        }
    }
}
