package blog.valerioemanuele.myriad;

import java.io.File;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

@Data
@Builder
public class FileWrapper implements Comparable<FileWrapper>{
	private File file;
	private Long size;
	private boolean isDir;
	
	@Override
	public int compareTo(@NonNull FileWrapper o) {
		return Long.signum(o.getSize() - getSize());
	}
}
