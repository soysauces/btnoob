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
    private String createAt;
    private String category;
    private String sourceName;
    private BigDecimal size;
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

    public String getCreateAt() {
        return createAt;
    }

    public void setCreateAt(String createAt) {
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

    public BigDecimal getSize() {
        return size;
    }

    public void setSize(BigDecimal size) {
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
}
