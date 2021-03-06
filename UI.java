import java.awt.BorderLayout;
import javax.swing.*;

public class UI {

    private static final int FRAME_WIDTH = 1100;
    private static final int FRAME_HEIGHT = 600;

    private final BoardPanel boardPanel;
    private final InfoPanel infoPanel;
    private final CommandPanel commandPanel;

    UI (Board board, Players players, Dice dice) {
        infoPanel = new InfoPanel();
        commandPanel = new CommandPanel();
        JFrame frame = new JFrame();
        boardPanel = new BoardPanel(board,players);
        frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        frame.setTitle("Backgammon");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.add(boardPanel, BorderLayout.LINE_START);
        frame.add(infoPanel, BorderLayout.LINE_END);
        frame.add(commandPanel, BorderLayout.PAGE_END);
        frame.setResizable(false);
        frame.setVisible(true);
    }

    public String getString() {
        return commandPanel.getString();
    }


    public void display() {
        boardPanel.refresh();
    }

    public void displayString(String string) {
        infoPanel.addText(string);
    }

    public void displayAnnouncement() {
        displayString("Welcome to Backgammon");
    }

    public void promptPlayerName() {
        displayString("Enter a player name:");
    }

    public void displayPlayerColor(Player player) {
        displayString(player + " uses " + player.getColorName() + " checkers.");
    }

    public void displayRoll(Player player, Dice dice) {
        displayString(player + " rolls " + dice);
    }

    public void displayDiceEqual() {
        displayString("Equal. Roll again");
    }

    public void displayDiceWinner(Player player) {
        displayString(player + " wins the roll and goes first.");
    }

    public void promptCommand(Player player) {
        displayString(player + " (" + player.getColorName() + ") enter your move, next, cheat or quit:");
    }


    public Command getCommand() {
        Command command;
        do {
            String commandString = commandPanel.getString();
            command = new Command(commandString);
            if (!command.isValid()) {
                displayString("Error: Command not valid.");
            }
        } while (!command.isValid());
        return command;
    }
}