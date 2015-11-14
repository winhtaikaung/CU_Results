package model;

/**
 * Created by winhtaikaung on 11/14/15.
 */

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.UUID;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

@JsonInclude(JsonInclude.Include.NON_NULL)

@JsonPropertyOrder({
        "roll_no",
        "name",
        "year",
        "major",
        "external"
})
public class Result extends RealmObject {
    @PrimaryKey
    private String id;
    @JsonProperty("roll_no")
    private String rollNo;
    @JsonProperty("name")
    private String name;
    @JsonProperty("year")
    private String year;
    @JsonProperty("major")
    private String major;
    @JsonProperty("external")
    private String external;


    public String getId() {
        if(id==null) {
            UUID uuid = UUID.randomUUID();
            return uuid.toString();
        }else{
            return id;
        }
    }

    public void setId(String id) {
        this.id = id;
    }

    /**
     *
     * @return
     * The rollNo
     */
    @JsonProperty("roll_no")
    public String getRollNo() {
        return rollNo;
    }

    /**
     *
     * @param rollNo
     * The roll_no
     */
    @JsonProperty("roll_no")
    public void setRollNo(String rollNo) {
        this.rollNo = rollNo;
    }

    /**
     *
     * @return
     * The name
     */
    @JsonProperty("name")
    public String getName() {
        return name;
    }

    /**
     *
     * @param name
     * The name
     */
    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return
     * The year
     */
    @JsonProperty("year")
    public String getYear() {
        return year;
    }

    /**
     *
     * @param year
     * The year
     */
    @JsonProperty("year")
    public void setYear(String year) {
        this.year = year;
    }

    /**
     *
     * @return
     * The major
     */
    @JsonProperty("major")
    public String getMajor() {
        if(major==null ) {
            String major="";
            return major;
        }else{
            return major;
        }
    }

    /**
     *
     * @param major
     * The major
     */
    @JsonProperty("major")
    public void setMajor(String major) {
        this.major = major;
    }

    /**
     *
     * @return
     * The external
     */
    @JsonProperty("external")
    public String getExternal() {
        return external;
    }

    /**
     *
     * @param external
     * The external
     */
    @JsonProperty("external")
    public void setExternal(String external) {
        this.external = external;
    }

}