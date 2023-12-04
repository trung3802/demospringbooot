package vn.vnpt.hdg.api.payload.response;

import java.util.List;

public class TreeItem {
	public int id;
    public String text;
    public Boolean expanded = false;
    public Boolean checked = false;
    public List<TreeItem> items;

    public TreeItem(int id, String text, Boolean expanded, Boolean checked, List<TreeItem> items) {
        this.id = id;
        this.text = text;
        this.expanded = expanded;
        this.checked = checked;
        this.items = items;
    }
}