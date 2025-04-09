/**
 * Berlin Brown
 * Nov 9, 2006
 */

package org.spirit.dao;

import java.util.List;

import org.spirit.bean.impl.BotListDocFile;

/**
 * This is class is used by botverse.
 * 
 * @author Berlin Brown
 * 
 */

public interface BotListDocFileDAO {
	
	 public void createDocFile(BotListDocFile file);
	 public List listFiles();
	 public List listFilesHistory();
}
