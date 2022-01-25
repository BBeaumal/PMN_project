package com.backend.qcmplus.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
public class ParcoursBean  {

    private Long idParcours;

    private String nomQuestionnaire;
    private double note;
    private Date dateFin;
    private Date dateRealisation;
 }
