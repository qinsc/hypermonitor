package hyper.momitor.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

/**
 * 文件测试接口
 *
 * @author qinsc (mailto:qinscx@gmail.com)
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
