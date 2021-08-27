package com.model;

import javax.persistence.*;
import java.sql.Date;

@SequenceGenerator(name = "seqPK", sequenceName = "seqPK")
@Entity
@Table(name = "ParsingData")
public class ParsingData {

    @Id
    @GeneratedValue(generator = "seqPK")
    @Column(name = "id", nullable = false)
    private Integer id;

    @Basic
    @Column(name = "siteName")
    private String siteName;

    @Basic
    @Column(name = "parsingDate")
    private Date parsingDate;

    @Column(name = "rawText", columnDefinition = "text")
    private String rawText;

    @Column(name = "parsingResult", columnDefinition = "text")
    private String parsingResult;

    public ParsingData() {

    }

    public ParsingData(String siteName, Date parsingDate, String rawText, String parsingResult) {
        this.siteName = siteName;
        this.parsingDate = parsingDate;
        this.rawText = rawText;
        this.parsingResult = parsingResult;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSiteName() {
        return siteName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }

    public Date getParsingDate() {
        return parsingDate;
    }

    public void setParsingDate(Date parsingDate) {
        this.parsingDate = parsingDate;
    }

    public String getRawText() {
        return rawText;
    }

    public void setRawText(String rawText) {
        this.rawText = rawText;
    }

    public String getParsingResult() {
        return parsingResult;
    }

    public void setParsingResult(String parsingResult) {
        this.parsingResult = parsingResult;
    }

}
