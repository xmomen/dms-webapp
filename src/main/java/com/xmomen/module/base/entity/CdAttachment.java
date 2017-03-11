package com.xmomen.module.base.entity;

import com.xmomen.framework.mybatis.model.BaseMybatisModel;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity
@Table(name = "cd_attachment")
public class CdAttachment extends BaseMybatisModel {
    /**
     * 主键
     */
    private Integer id;

    /**
     * 业务主键
     */
    private String businessId;

    /**
     * 附件类型
     */
    private String type;

    /**
     * 附件名称
     */
    private String fileName;

    /**
     * 后缀
     */
    private String fileExtend;

    /**
     * 存储路径
     */
    private String filePath;

    /**
     * 上传时间
     */
    private Date uploadDate;

    /**
     * 上传人
     */
    private String uploadUser;

    @Column(name = "id")
    @Id
    @GeneratedValue(generator = "UUIDGenerator")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
        if(id == null){
              removeValidField("id");
              return;
        }
        addValidField("id");
    }

    @Column(name = "business_id")
    public String getBusinessId() {
        return businessId;
    }

    public void setBusinessId(String businessId) {
        this.businessId = businessId;
        if(businessId == null){
              removeValidField("businessId");
              return;
        }
        addValidField("businessId");
    }

    @Column(name = "type")
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
        if(type == null){
              removeValidField("type");
              return;
        }
        addValidField("type");
    }

    @Column(name = "file_name")
    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
        if(fileName == null){
              removeValidField("fileName");
              return;
        }
        addValidField("fileName");
    }

    @Column(name = "file_extend")
    public String getFileExtend() {
        return fileExtend;
    }

    public void setFileExtend(String fileExtend) {
        this.fileExtend = fileExtend;
        if(fileExtend == null){
              removeValidField("fileExtend");
              return;
        }
        addValidField("fileExtend");
    }

    @Column(name = "file_path")
    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
        if(filePath == null){
              removeValidField("filePath");
              return;
        }
        addValidField("filePath");
    }

    @Column(name = "upload_date")
    public Date getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(Date uploadDate) {
        this.uploadDate = uploadDate;
        if(uploadDate == null){
              removeValidField("uploadDate");
              return;
        }
        addValidField("uploadDate");
    }

    @Column(name = "upload_user")
    public String getUploadUser() {
        return uploadUser;
    }

    public void setUploadUser(String uploadUser) {
        this.uploadUser = uploadUser;
        if(uploadUser == null){
              removeValidField("uploadUser");
              return;
        }
        addValidField("uploadUser");
    }
}