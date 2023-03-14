package com.brexson.projectmanagementapp.dto;


public interface ChartData {
    public String getLabel();

    //	getValue was renamed to getCount because "value" seems to be a reserved word is H2 SQL statement
//	public Long getValue();
    public Long getCount();

}
