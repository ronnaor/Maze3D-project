package presenter;

import java.util.Observable;
import java.util.Observer;

import algorithms.mazeGenarators.Maze3d;
import algorithms.mazeGenarators.Position;
import algorithms.search.Solution;
import model.Model;
import view.View;

/**
 * class Presenter that implements Observer will coordinate between a model and a view
 *
 */
public class Presenter implements Observer {

	private Model model;
	private View view;
	
	/**
	 * Ctor for Presenter
	 * @param model the model the Presenter will work with
	 * @param view the view the Presenter will work with
	 */
	public Presenter(Model model,  View view) {
		this.model = model;
		this.view = view;
	}
	/**
	 * get the model the presenter work with
	 * @return the model
	 */
	public Model getModel() {
		return model;
	}
	/**
	 * set the model the presenter work with
	 * @param model
	 */
	public void setModel(Model model) {
		this.model = model;
	}
	/**
	 * get the view the presenter work with
	 * @return the view
	 */
	public View getView() {
		return view;
	}
	/**
	 * set the view the presenter work with
	 * @param view
	 */
	public void setView(View view) {
		this.view = view;
	}

	/**
	 * @Override for Observer method update
	 */
	@Override
	public void update(Observable obs, Object obj) {
		String[] args = (String[])obj;
		if (obs == model)
		{
			switch(args[0])
			{
			case "printDir": 
				view.printDir(args);
				break;
			case "mazeGenerated":
				view.printOutput(args[1]);
				break;
			case "mazeSaved":
				view.printOutput(args[1]);
				break;
			case "mazeloaded":
				view.printOutput(args[1]);
				break;
			case "mazeSolved":
				view.printOutput(args[1]);
				break;
			case "exit":
				view.printOutput("Goodbye");
			}
		}
		if (obs == view)
		{
			switch(args[0])
			{
			case "dir": 
				model.getDir(args);
				break;
			case "generate 3d maze":
				model.generate3DMaze(args);
				break;
			case "display":
				Maze3d maze = model.getMaze(args);
				if (maze !=null)
				{
					view.displayMaze(maze);
				}
				else
				{
					view.printOutput("the maze does not exists");
				}
				break;
			case "display cross section by":
				int[][] arr = model.getCrossSectionBy(args);
				if (arr!=null)
				{
					view.displayCrossSectionBy(arr, args[1], args[2]);
				}
				else
				{
					view.printOutput("error in display cross section");
				}
				break;
			case "save maze":
				model.saveMaze(args);
				break;
			case "load maze":
				model.loadMaze(args);
				break;
			case "maze size":
				int size= model.getMazeSize(args);
				if (size!=0)
				{
					view.displayMazeSize(size,args[1]);
				}
				else
				{
					view.printOutput("error in display maze size");
				}
				break;
			case "file size":
				long l = model.getFileSize(args);
				if (l!=0)
				{
					view.displayFileSize(l, args[1]);
				}
				else
				{
					view.printOutput("error in display file size");
				}
				break;
			case "solve":
				model.solve(args);
				break;
			case "display solution":
				Solution<Position> sol= model.getSoultion(args);
				if (sol != null)
				{
					view.displaySolution(sol);
				}
				else
				{
					view.printOutput("solution not found");
				}
				break;
			case "exit":
				model.exit(args);
				break;
			}

		}

	}

	
	/**
	 * casting and checking from string to integer
	 * @param str the variable we want to change
	 * @return The integer we changed the string to
	 */
	public static Integer tryParseInt(String str) {
		   try {
		      return Integer.parseInt(str);
		   } catch (NumberFormatException ex) {
		      return null;
		   }
		}
	
}