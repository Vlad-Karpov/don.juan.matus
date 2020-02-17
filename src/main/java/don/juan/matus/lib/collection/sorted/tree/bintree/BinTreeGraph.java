package don.juan.matus.lib.collection.sorted.tree.bintree;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.HashMap;

/**
 * Grahp binary tree
 */
public class BinTreeGraph<T extends Comparable<T>> {

    private BinTreeBase<T> tree;
    private JFrame frame;
    private JPanel pane;
    private Container cp;
    private JScrollPane jspane;
    private Long maxLevel = -1L;
    private HashMap<BinTreeNodeInterface<T>, DopInfo> dopInfo = new HashMap();
    private Long maxX = 900L;
    private Long maxY = 700L;

    public BinTreeGraph() {
       this(new BinTreeBase<T>());
    }

    public BinTreeGraph(BinTreeBase<T> theTree) {
        super();
        tree = theTree;
    }

    private void init() {
        tree.clearTree();
        maxLevel = -1L;
        maxX = 900L;
        maxY = 700L;
        dopInfo.clear();
    }

    public void updateDopInfo(BinTreeNodeInterface<T> theObject, DopInfo theDopInfo) {
        DopInfo di = dopInfo.get(theObject);
        if (di != null) {
            if (theDopInfo.currentNodeCount != null) di.currentNodeCount = theDopInfo.currentNodeCount;
            if (theDopInfo.x != null) di.x = theDopInfo.x;
            if (theDopInfo.y != null) di.y = theDopInfo.y;
            dopInfo.put(theObject, di);
        } else {
            dopInfo.put(theObject, theDopInfo);
        }
    }

    public void paintQ(final Graphics g) {
        tree.passStraight(new BinTreePassEvent<BinTreeNodeInterface<T>>() {

            private Long level = -2L;
            private StringBuilder nodeOrder = new StringBuilder();

            public void onPass(BinTreeNodeInterface<T> theObject) {
                updateDopInfo(theObject, new DopInfo(Long.parseLong(nodeOrder.toString(), 2), level));
            }

            public Long incLevel(BinTreeNodeInterface<T> theObject) {
                Long result = ++level;
                if (result > maxLevel) maxLevel = result;
                return result;
            }
            public Long decLevel(BinTreeNodeInterface<T> theObject) {
                return --level;
            }
            public Long incLeft(BinTreeNodeInterface<T> theObject) {
                nodeOrder.append("1");
                return Long.parseLong(nodeOrder.toString(), 2);
            }
            public Long decLeft(BinTreeNodeInterface<T> theObject) {
                nodeOrder.setLength(nodeOrder.length() - 1);
                return getaLong();
            }
            public Long incRight(BinTreeNodeInterface<T> theObject) {
                nodeOrder.append("0");
                return Long.parseLong(nodeOrder.toString(), 2);
            }
            public Long decRight(BinTreeNodeInterface<T> theObject) {
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

            @Override
            public void onNodeCompleted(BinTreeNodeInterface<T> theObject) {

            }

            @Override
            public void onPassCompleted() {

            }
        });
        maxX = 45L + (1L << maxLevel) * 100L;
        maxY = 50L + maxLevel * 53L;
        tree.passStraight(new BinTreePassEvent<BinTreeNodeInterface<T>>() {

            private void paint(BinTreeNodeInterface<T> theObject) {
                DopInfo di = dopInfo.get(theObject);
                g.setColor(Color.gray);
                int xx = 20;
                int yy = 20 + di.level.intValue() * 30;
                g.drawRect(xx, yy, 20, 20);
                g.drawString(di.level.toString(), xx + 7,  yy + 15);

                Long rowCount = (1L << di.level);
                Long maxLevelRowCount = (1L << maxLevel);
                Long currentNodeRowIndex = rowCount - (di.currentNodeCount - calcItemUpToLevelCount(di.level));

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

                updateDopInfo(theObject, new DopInfo(xx, yy));

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

            public void onPass(BinTreeNodeInterface<T> theObject) {
            }

            public Long incLevel(BinTreeNodeInterface<T> theObject) {
                return 0L;
            }

            public Long decLevel(BinTreeNodeInterface<T> theObject) {
                return 0L;
            }

            public Long incLeft(BinTreeNodeInterface<T> theObject) {
                return null;
            }
            public Long decLeft(BinTreeNodeInterface<T> theObject) {
                return null;
            }

            public Long incRight(BinTreeNodeInterface<T> theObject) {
                return null;
            }
            public Long decRight(BinTreeNodeInterface<T> theObject) {
                return null;
            }

            @Override
            public void onNodeCompleted(BinTreeNodeInterface<T> theObject) {
                paint(theObject);
            }

            @Override
            public void onPassCompleted() {

            }

        });
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
                dopInfo.clear();
                SwingUtilities.updateComponentTreeUI(frame);
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
                dopInfo.clear();
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
                dopInfo.clear();
                SwingUtilities.updateComponentTreeUI(frame);
            }
        });
        JButton jButtonClear = new JButton("clear");
        jButtonClear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("clear tree");
                jTextField.setText("");
                jTextField.requestFocusInWindow();
                init();
                SwingUtilities.updateComponentTreeUI(frame);
            }
        });

        JButton jButtonBalance = new JButton("balance");
        jButtonBalance.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("balance");
                jTextField.setText("");
                jTextField.requestFocusInWindow();
                tree.rebalanceTree();
                dopInfo.clear();
                SwingUtilities.updateComponentTreeUI(frame);
            }
        });


        jMenuBar.add(jTextField);
        jMenuBar.add(jButtonAdd);
        jMenuBar.add(jButtonDel);
        jMenuBar.add(jButtonCheck);
        jMenuBar.add(jButtonClear);
        jMenuBar.add(jButtonBalance);
        frame.setJMenuBar(jMenuBar);
        cp = frame.getContentPane();
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

            @Override
            protected void processMouseEvent(MouseEvent e) {
                super.processMouseEvent(e);
            }

        };
        jspane = new JScrollPane(pane, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
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

        public Long currentNodeCount;
        public Long level;
        public Integer x;
        public Integer y;

        public DopInfo() {
            this.currentNodeCount = null;
            this.level = null;
            this.x = null;
            this.y = null;
        }

        public DopInfo(Long currentNodeCount) {
            this.currentNodeCount = currentNodeCount;
            this.level = null;
            this.x = null;
            this.y = null;
        }

        public DopInfo(Long currentNodeCount, Long level) {
            this.currentNodeCount = currentNodeCount;
            this.level = level;
            this.x = null;
            this.y = null;
        }

        public DopInfo(int x, int y) {
            this.currentNodeCount = null;
            this.level = null;
            this.x = x;
            this.y = y;
        }

        public DopInfo(long currentNodeCount, int x, int y) {
            this.currentNodeCount = currentNodeCount;
            this.level = null;
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
