package com.thoughtworks.jimmy.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

import com.thoughtworks.jimmy.enumeration.Color;

@Entity
@Table(name = "WO_TAG")
public class SelfTag {
    @Id
    @Column(name = "ID")
//    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @Column(name = "TAG", nullable = false, unique = true, length = 30)
    private String tag;

    @Column(name = "COLOR", nullable = true)
    @Enumerated(EnumType.STRING)
    private Color color;

    public SelfTag() {
    }

    public SelfTag(Integer id, String tag, Color color) {
        this.id = id;
        this.tag = tag;
        this.color = color;
    }

    public String getTag() {
        return tag;
    }

    public Integer getId() {
        return id;
    }
}
