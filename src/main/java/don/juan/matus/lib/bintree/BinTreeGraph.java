package don.juan.matus.lib.bintree;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

/**
 * Граф двоичного дерева.
 */
public class BinTreeGraph<T extends Comparable<T>> {

    private BinTreeInterface<T> tree;
    private JFrame frame;
    private JPanel pane;
    private Long maxLevel = -1L;
    private HashMap<BinTreeNodeInterface<T>, DopInfo> dopInfo = new HashMap();
    private Long maxX = 900L;
    private Long maxY = 700L;



    public BinTreeGraph(BinTreeInterface<T> theTree) {
        super();
        tree = theTree;
    }

    public void paintQ(final Graphics g) {
        tree.passStraight(new BinTreePassEvent<BinTreeNodeInterface<T>>() {

            private Long level = -2L;

            public void onPass(BinTreeNodeInterface<T> theObject) {
            }

            public Long incLevel() {
                Long result = ++level;
                if (result > maxLevel) maxLevel = result;
                return result;
            }
            public Long decLevel() {
                return --level;
            }
            public Long incLeft() {
                return null;
            }
            public Long decLeft() {
                return null;
            }
            public Long incRight() {
                return null;
            }
            public Long decRight() {
                return null;
            }
        });
        maxX = 45L + (1L << maxLevel) * 50L;
        maxY = 50L + maxLevel * 53L;
//        if (maxX > 900 || maxY > 700) {
//            pane.setSize(new Dimension(maxX.intValue(), maxY.intValue()));
//        }
        tree.passStraight(new BinTreePassEvent<BinTreeNodeInterface<T>>() {

            private Long level = -2L;
            private StringBuilder nodeOrder = new StringBuilder();

            public void onPass(BinTreeNodeInterface<T> theObject) {
                g.setColor(Color.gray);
                int xx = 20;
                int yy = 20 + level.intValue() * 30;
                g.drawRect(xx, yy, 20, 20);
                g.drawString(level.toString(), xx + 7,  yy + 15);

                Long currentNodeCount = Long.parseLong(nodeOrder.toString(), 2);
                Long rowCount = (1L << level);
                Long maxLevelRowCount = (1L << maxLevel);
                Long currentNodeRowIndex = rowCount - (currentNodeCount - calcItemUpToLevelCount(level));

                Long xCountMax = 100L * maxLevelRowCount;
                Long xRowCount = xCountMax / rowCount;

                xx = xx + 25 + currentNodeRowIndex.intValue() * xRowCount.intValue() + xRowCount.intValue() / 2;

                String outStr = theObject.desc();
                if (outStr.contains("RED")) {
                    g.setColor(Color.red);
                } else if (outStr.contains("BLACK")) {
                    g.setColor(Color.black);
                } else {
                    g.setColor(Color.gray);
                }
                //g.drawRect(xx, yy, 30, 20);
                g.drawString(outStr, xx + 2,  yy + 15);
                g.setColor(Color.gray);

                DopInfo di = new DopInfo(xx, yy);
                dopInfo.put(theObject, di);

                if (theObject.getLeft() != null) {
                    di = dopInfo.get(theObject.getLeft());
                    if (di != null) {
                        g.drawLine(xx, yy + 20, di.x + 7, di.y);
                    }
                }
                if (theObject.getRight() != null) {
                    di = dopInfo.get(theObject.getRight());
                    if (di != null) {
                        g.drawLine(xx + 30, yy + 20, di.x + 7, di.y);
                    }
                }

            }

            public Long incLevel() {
                Long result = ++level;
                return result;
            }

            public Long decLevel() {
                return --level;
            }

            public Long incLeft() {
                nodeOrder.append("1");
                return Long.parseLong(nodeOrder.toString(), 2);
            }
            public Long decLeft() {
                nodeOrder.setLength(nodeOrder.length() - 1);
                return getaLong();
            }

            public Long incRight() {
                nodeOrder.append("0");
                return Long.parseLong(nodeOrder.toString(), 2);
            }
            public Long decRight() {
                nodeOrder.setLength(nodeOrder.length() - 1);
                return getaLong();
            }

            private Long getaLong() {
                if (nodeOrder.length() > 0) {
                    return Long.parseLong(nodeOrder.toString(), 2);
                } else {
                    return 0L;
                }
            }

        });
    }

    private void refreshFrame() {
        frame.invalidate();
        frame.validate();
        frame.repaint();
    }

    private void createAndShowGUI() {
        JFrame.setDefaultLookAndFeelDecorated(true);
        frame = new JFrame("Let's go");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JMenuBar jMenuBar = new JMenuBar();
        final JTextField jTextField = new JTextField();
        JButton jButtonAdd = new JButton("add");
        jButtonAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Long newValue = Long.parseLong(jTextField.getText());
                tree.add((T)newValue);
                System.out.println("add " + newValue);
                jTextField.setText("");
                jTextField.requestFocusInWindow();
                SwingUtilities.updateComponentTreeUI(frame);
                refreshFrame();
            }
        });
        JButton jButtonDel = new JButton("del");
        jButtonDel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Long newValue = Long.parseLong(jTextField.getText());
                tree.remove((T)newValue);
                System.out.println("del " + newValue);
                jTextField.setText("");
                jTextField.requestFocusInWindow();
                SwingUtilities.updateComponentTreeUI(frame);
            }
        });
        JButton jButtonCheck = new JButton("check");
        jButtonCheck.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tree.checkStructure(new BinTreeCheckPassEvent<T>() {

                    private String errorMessage;

                    @Override
                    public void onPass(BinTreeIterator<T> leftIterator, BinTreeIterator<T> rightIterator, BinTreeNodeInterface<T> currentNode, BinTreeNodeInterface<T> previousNode) {
                        System.out.println(errorMessage
                                + "\r\n Current node: " + currentNode
                                + "\r\n Previous node: " + previousNode
                                + "\r\n Left iterator: " + leftIterator
                                + "\r\n Right iterator: " + rightIterator);
                    }

                    @Override
                    public void setErrorMessage(String errMsg) {
                        errorMessage = errMsg;
                    }

                    @Override
                    public String getErrorMessage() {
                        return errorMessage;
                    }

                });
                jTextField.requestFocusInWindow();
                SwingUtilities.updateComponentTreeUI(frame);
            }
        });
        jMenuBar.add(jTextField);
        jMenuBar.add(jButtonAdd);
        jMenuBar.add(jButtonDel);
        jMenuBar.add(jButtonCheck);
        frame.setJMenuBar(jMenuBar);
        Container cp = frame.getContentPane();
        pane = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                paintQ(g);
            }

            @Override
            public Dimension getPreferredSize() {
                return new Dimension(maxX.intValue(), maxY.intValue());
            }
        };
        JScrollPane jspane = new JScrollPane(pane, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        cp.add(jspane);
        frame.setSize(new Dimension(900, 700));
        frame.pack();
        frame.setVisible(true);
    }

    public void letsGo() {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }

    private class DopInfo {
        public int x;
        public int y;

        public DopInfo(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private Long calcItemUpToLevelCount(Long theLevel) {
        Long result = 0L;
        for(long i = 0L; i < theLevel; i++) {
            result = result + (1 << i);
        }
        return result;
    }

}
