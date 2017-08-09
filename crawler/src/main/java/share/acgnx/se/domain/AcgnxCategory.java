package share.acgnx.se.domain;

import jdk.nashorn.internal.objects.annotations.Getter;
import jdk.nashorn.internal.objects.annotations.Setter;
import us.codecraft.webmagic.model.annotation.ExtractBy;
import us.codecraft.webmagic.model.annotation.Formatter;

import java.lang.annotation.Target;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by zz on 2017/8/6.
 */
public class AcgnxCategory {

    private int id;
    private Date createAt;
    private String category;
    private String sourceName;
    private String size;
    private String sourceUrl;
    private String fluxLingage;
    private int uploadNo;
    private int downloadNo;
    private int completeNo;
    private String hash;
    private String creator;

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public String getSourceUrl() {
        return sourceUrl;
    }

    public void setSourceUrl(String sourceUrl) {
        this.sourceUrl = sourceUrl;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSourceName() {
        return sourceName;
    }

    public void setSourceName(String sourceName) {
        this.sourceName = sourceName;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getFluxLingage() {
        return fluxLingage;
    }

    public void setFluxLingage(String fluxLingage) {
        this.fluxLingage = fluxLingage;
    }

    public int getUploadNo() {
        return uploadNo;
    }

    public void setUploadNo(int uploadNo) {
        this.uploadNo = uploadNo;
    }

    public int getDownloadNo() {
        return downloadNo;
    }

    public void setDownloadNo(int downloadNo) {
        this.downloadNo = downloadNo;
    }

    public int getCompleteNo() {
        return completeNo;
    }

    public void setCompleteNo(int completeNo) {
        this.completeNo = completeNo;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    @Override
    public String toString() {
        return "AcgnxCategory{" +
                "id=" + id +
                ", createAt=" + createAt +
                ", category='" + category + '\'' +
                ", sourceName='" + sourceName + '\'' +
                ", size='" + size + '\'' +
                ", sourceUrl='" + sourceUrl + '\'' +
                ", fluxLingage='" + fluxLingage + '\'' +
                ", uploadNo=" + uploadNo +
                ", downloadNo=" + downloadNo +
                ", completeNo=" + completeNo +
                ", hash='" + hash + '\'' +
                ", creator='" + creator + '\'' +
                '}';
    }
}
