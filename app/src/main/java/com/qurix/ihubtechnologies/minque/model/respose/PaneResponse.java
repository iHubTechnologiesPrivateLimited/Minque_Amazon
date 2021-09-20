package com.qurix.ihubtechnologies.minque.model.respose;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;

import com.qurix.ihubtechnologies.minque.storage.PlayListTypeConverter;

import java.io.Serializable;
import java.util.List;
@Entity
public class PaneResponse implements Serializable {


    /**
     * paneId : 1
     * xAxis : 0
     * yAxis : 0
     * width : 75
     * height : 75
     * playlistType : AndriodQueue
     * listOfPlayList : [{"filePath":null,"fileType":"AndroidQueue","fileDuration":null}]
     * active : true
     */
    @PrimaryKey(autoGenerate = true)
    private int id;
    private int paneId;
    private int xAxis;
    private int yAxis;
    private int width;
    private int height;
    private String playlistType;
    private boolean active;
    @TypeConverters(PlayListTypeConverter.class)
    private List<ListOfPlayListBean> listOfPlayList;

    public PaneResponse(int id, int paneId, int xAxis, int yAxis, int width, int height, String playlistType, boolean active, List<ListOfPlayListBean> listOfPlayList) {

       this.id = id;
       this.paneId = paneId;
        this.xAxis = xAxis;
        this.yAxis = yAxis;
        this.width = width;
        this.height = height;
        this.playlistType = playlistType;
        this.active = active;
        this.listOfPlayList = listOfPlayList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getxAxis() {
        return xAxis;
    }

    public void setxAxis(int xAxis) {
        this.xAxis = xAxis;
    }

    public int getyAxis() {
        return yAxis;
    }

    public void setyAxis(int yAxis) {
        this.yAxis = yAxis;
    }

    public int getPaneId() {
        return paneId;
    }

    public void setPaneId(int paneId) {
        this.paneId = paneId;
    }

    public int getXAxis() {
        return xAxis;
    }

    public void setXAxis(int xAxis) {
        this.xAxis = xAxis;
    }

    public int getYAxis() {
        return yAxis;
    }

    public void setYAxis(int yAxis) {
        this.yAxis = yAxis;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getPlaylistType() {
        return playlistType;
    }

    public void setPlaylistType(String playlistType) {
        this.playlistType = playlistType;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public List<ListOfPlayListBean> getListOfPlayList() {
        return listOfPlayList;
    }

    public void setListOfPlayList(List<ListOfPlayListBean> listOfPlayList) {
        this.listOfPlayList = listOfPlayList;
    }

    public static class ListOfPlayListBean {
        /**
         * filePath : null
         * fileType : AndroidQueue
         * fileDuration : null
         */

        private String  filePath;
        private String fileType;
        private String fileDuration;

        public String getFilePath() {
            return filePath;
        }

        public void setFilePath(String filePath) {
            this.filePath = filePath;
        }

        public String getFileType() {
            return fileType;
        }

        public void setFileType(String fileType) {
            this.fileType = fileType;
        }

        public Object getFileDuration() {
            return fileDuration;
        }

        public void setFileDuration(String fileDuration) {
            this.fileDuration = fileDuration;
        }
    }

    @Override
    public String toString() {
        return "PaneResponse{" +
                "id=" + id +
                ", paneId=" + paneId +
                ", xAxis=" + xAxis +
                ", yAxis=" + yAxis +
                ", width=" + width +
                ", height=" + height +
                ", playlistType='" + playlistType + '\'' +
                ", active=" + active +
                ", listOfPlayList=" + listOfPlayList +
                '}';
    }
}
