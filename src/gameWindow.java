import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Ross on 27/11/2016.
 */
public class gameWindow extends JFrame
{
    private JPanel gameContent;
    private JLabel[][] gameGrid = new JLabel[4][4];
    private masterSpaceShip masterShip;
    private gameManager gameController;

    public gameWindow(masterSpaceShip Ship, gameManager Controller)
    {
        masterShip = Ship;
        gameController = Controller;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 895, 1200);
        setBackground(Color.DARK_GRAY);
        gameContent = new JPanel();
        gameContent.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(gameContent);
        gameContent.setLayout(null);
        gameContent.setBackground(Color.DARK_GRAY);

        for(Integer column = 0; column < 4; column++)
        {
            for(Integer row = 0; row < 4; row++)
            {
                JLabel gameSquare = new JLabel("(" + column.toString() + "," + row.toString() + ")", SwingConstants.RIGHT);
                gameSquare.setLocation(column*200, row*200);
                gameSquare.setBounds(column*220, row*180, 220, 180);
                gameSquare.setBorder(BorderFactory.createLineBorder(Color.black, 1));
                gameSquare.setOpaque(true);
                if(column == row)
                {
                    gameSquare.setBackground(Color.white);
                }
                else
                {
                    gameSquare.setBackground(Color.CYAN);
                }
                gameGrid[column][row] = gameSquare;
                gameContent.add(gameGrid[column][row]);
            }
        }

        JButton movementButton = new JButton("Movement");
        movementButton.setBounds(0, 720, 200, 100);
        movementButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent arg0)
            {
                gameController.gameUpdate(masterShip);
                UpdateGrid();
            }
        });
        gameContent.add(movementButton);

        JButton undoButton = new JButton("Undo");
        undoButton.setBounds(200, 720, 200, 100);
        undoButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                gameController.getGameHistory().undoLastMove(gameController.getGrid(), gameController.turnCounter);
                UpdateGrid();
            }
        });
        gameContent.add(undoButton);

        JButton changeMode = new JButton("Change ship's mode");
        JLabel currentMode = new JLabel("Ship is in Defensive Mode!");
        changeMode.setBounds(0, 820, 200, 100);
        currentMode.setBounds(200, 820, 200, 100);
        currentMode.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        currentMode.setOpaque(true);
        changeMode.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                gameController.changeMode(masterShip);

                if(currentMode.getText().equals("Ship is in Defensive Mode!"))
                {
                    currentMode.setText("Ship is in Offensive Mode!");

                }
                else
                {
                    currentMode.setText("Ship is in Defensive Mode!");
                }

                UpdateGrid();
            }
        });
        gameContent.add(changeMode);
        gameContent.add(currentMode);
    }

    private void UpdateGrid()
    {
        int i = 0;
        for(Integer column = 0; column < 4; column++)
        {
            for (Integer row = 0; row < 4; row++)
            {
                gameGrid[column][row].setText(gameController.getGrid().updateGUIGrid(column, row));
                gameGrid[column][row].setForeground(Color.DARK_GRAY);
            }
        }
        gameGrid[(int)masterShip.currentPos.getX()][(int)masterShip.currentPos.getY()].setForeground(Color.black);
    }
}
