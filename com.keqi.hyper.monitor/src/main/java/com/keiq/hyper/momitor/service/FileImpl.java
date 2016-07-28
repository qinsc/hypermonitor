/*******************************************************************************
 *
 *==============================================================================
 *
 * Copyright (c) 2001-2016 Primeton Technologies, Ltd.
 * All rights reserved.
 * 
 * Created on 2016年7月28日 下午2:46:14
 *******************************************************************************/


package com.keiq.hyper.momitor.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

/**
 * TODO 此处填写 class 信息
 *
 * @author qinsc (mailto:qinsc@primeton.com)
 */
@Service("iFileService")
public class FileImpl implements IFile{

	/* (non-Javadoc)
	 * @see test.rest.file.IFile#listFiles()
	 */
	@Override
	public List<String> listFiles() {
		return Arrays.asList("file1", "file2");
	}

}
