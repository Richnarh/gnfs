/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gnfs.model;

import java.util.List;

/**
 *
 * @author Richard Narh
 */
public final class Params {
    private Object obj;
    private String id;
    
    private String rootNode;
    private List<String> children;
    
    private final static Params INSTANCE = new Params();

    public Params() {
    }

    public Params(Object obj) {
        this.obj = obj;
    }
    
    public Params addData(Object obj){
        this.obj = obj;
        return this;
    }
    public Params addId(String id){
        this.id = id;
        return this;
    }
    
    public static Params newInstance(){
        return INSTANCE;
    }

    public Object getData() {
        return obj;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRootNode() {
        return rootNode;
    }

    public void setRootNode(String rootNode) {
        this.rootNode = rootNode;
    }

    public List<String> getChildren() {
        return children;
    }

    public void setChildren(List<String> children) {
        this.children = children;
    }

}
