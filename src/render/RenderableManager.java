package render;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class RenderableManager {
	
	private static final RenderableManager instance = new RenderableManager();

	private List<Renderable> renderableList;
	private Comparator<Renderable> comparator;

	public RenderableManager() {
		renderableList = new ArrayList<Renderable>();
		comparator = (Renderable o1, Renderable o2) -> {
			if (o1.getZ() > o2.getZ())
				return 1;
			return -1;
		};
	}

	public static RenderableManager getInstance() {
		return instance;
	}

	public void add(Renderable renderable) {
		renderableList.add(renderable);
		Collections.sort(renderableList, comparator);
	}

	public void updateRenderableList() {
		for (int i = renderableList.size() - 1; i >= 0; i--) {
			if (renderableList.get(i).isDestroyed())
				renderableList.remove(i);
		}
	}

	public List<Renderable> getRenderableList() {
		return renderableList;
	}
}
