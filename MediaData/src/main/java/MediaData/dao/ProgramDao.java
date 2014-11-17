package MediaData.dao;

import java.util.List;

import MediaData.entity.Program;

public interface ProgramDao {
	//public abstract void saveProgram(Program program);
	void saveProgram(Program program);
	Program getProgram(String title);
	Program getProgram(long id);
	List<Program> getPrograms();
}
