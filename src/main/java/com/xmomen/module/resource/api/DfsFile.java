package com.xmomen.module.resource.api;

import java.util.Map;

public class DfsFile {

    /**
     * file data
     */
    private byte[] data;

    /**
     * key,not used in FastDfs
     */
    private String key;

    /**
     * metadata for file
     */
    private Map<String, String> metadata;

    public DfsFile() {

    }
    /**
     * constructor with data, key and metadata
     *
     * @param key key,not used in FastDfs
     * @param data file data
     * @param metadata metadata for file
     */
    public DfsFile(String key, byte[] data, Map<String, String> metadata) {
        this.key = key;
        this.data = data;
        this.metadata = metadata;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Map<String, String> getMetadata() {
        return metadata;
    }

    public void setMetadata(Map<String, String> metadata) {
        this.metadata = metadata;
    }

}
