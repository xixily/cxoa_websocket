package com.chaoxing.oa.entity.page.common;

public class ExportModelVo {
	private String fieldId;
	private String fieldName;
	private String dicName;
	private int fieldIndex;

    public ExportModelVo(String name, String id){
        this.fieldName = name;
        this.fieldId = id;
    }

    public ExportModelVo(){

    }
	
	public String getFieldId() {
		return fieldId;
	}
	public void setFieldId(String fieldId) {
		this.fieldId = fieldId;
	}

	public String getFieldName() {
		return fieldName;
	}
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}
	public String getDicName() {
		return dicName;
	}
	public void setDicName(String dicName) {
		this.dicName = dicName;
	}

	public int getFieldIndex() {
		return fieldIndex;
	}
	public void setFieldIndex(int fieldIndex) {
		this.fieldIndex = fieldIndex;
	}

	
}
