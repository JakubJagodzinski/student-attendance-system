package entities;

public class Term {
    //term id jest niedostepny dla usera

    private Integer termId;
    private String termName;
    private String termGroup;
    private String termDate;
    private String termHourStart;
    private String termHourEnd;

    public Term(String termName, String termGroup, String termDate, String termHourStart, String termHourEnd) {
        this.termName = termName;
        this.termGroup = termGroup;
        this.termDate = termDate;
        this.termHourStart = termHourStart;
        this.termHourEnd = termHourEnd;
    }

    public Integer getTermId() {
        return termId;
    }

    public String getTermName() {
        return termName;
    }

    public void setTermName(String termName) {
        this.termName = termName;
    }

    public String getTermGroup() {
        return termGroup;
    }

    public void setTermGroup(String termGroup) {
        this.termGroup = termGroup;
    }

    public String getTermDate() {
        return termDate;
    }

    public void setTermDate(String termDate) {
        this.termDate = termDate;
    }

    public String getTermHourStart() {
        return termHourStart;
    }

    public void setTermHourStart(String termHourStart) {
        this.termHourStart = termHourStart;
    }

    public String getTermHourEnd() {
        return termHourEnd;
    }

    public void setTermHourEnd(String termHourEnd) {
        this.termHourEnd = termHourEnd;
    }

    @Override
    public String toString() {
        return "[" + termName + "] " + termGroup + " " + termHourStart + " " + termHourEnd;
    }

}
