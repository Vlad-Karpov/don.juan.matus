package don.juan.matus.lib.collection.sorted.tree.bintree;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;

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
    private HashMap<BinTreeNodeInterface<T>, DopInfo> dopInfo = new HashMap<>();
    private Long maxX = 900L;
    private Long maxY = 500L;

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
        maxY = 500L;
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
                Integer wl = 0;
                if (theObject.getLeft() != null) {
                    DopInfo dil = dopInfo.get(theObject.getLeft());
                    wl = dil.width;
                }
                Integer wr = 0;
                if (theObject.getRight() != null) {
                    DopInfo dir = dopInfo.get(theObject.getRight());
                    wr = dir.width;
                }
                DopInfo di = dopInfo.get(theObject);
                di.w = theObject.desc().length() * 6 + 4;
                di.width = Math.max(di.w, wl + wr)  + 20;
            }

            @Override
            public void onPassCompleted() {

            }

        });
        if (tree.root.getLeft() != null) {
            maxX = dopInfo.get(tree.root.getLeft()).width.longValue();
        } else {
            maxX = 45L + (1L << maxLevel) * 100L;
        }
        maxY = 50L + maxLevel * 53L;
        tree.passStraight(new BinTreePassEvent<BinTreeNodeInterface<T>>() {


            private void paint(BinTreeNodeInterface<T> theObject) {

                String outStr = tree.root.desc();
                g.drawString(outStr, 2,  20);

                DopInfo di = dopInfo.get(theObject);
                g.setColor(Color.gray);
                int xx = 20;
                int yy = 30 + di.level.intValue() * 30;
                g.drawRect(xx, yy, 20, 20);
                g.drawString(di.level.toString(), xx + 7,  yy + 15);

                int prevW = getPrevW(theObject);
                xx = 45 + prevW + di.width / 2;

                outStr = theObject.desc();
                if (outStr.contains("RED")) {
                    g.setColor(Color.red);
                } else if (outStr.contains("BLACK")) {
                    g.setColor(Color.black);
                } else {
                    g.setColor(Color.gray);
                }
                g.drawString(outStr, xx + 2,  yy + 15);
                g.setColor(Color.gray);
                updateDopInfo(theObject, new DopInfo(xx, yy));

                if (theObject.getLeft() != null) {
                    DopInfo dil = dopInfo.get(theObject.getLeft());
                    if (dil != null) {
                        g.drawLine(xx, yy + 20, dil.x + 7, dil.y);
                    }
                }
                if (theObject.getRight() != null) {
                    DopInfo dir = dopInfo.get(theObject.getRight());
                    if (dir != null) {
                        g.drawLine(xx + 10, yy + 20, dir.x + 7, dir.y);
                    }
                }

            }

            private int getPrevW(BinTreeNodeInterface<T> theObject) {
                int result = 0;
                BinTreeNodeInterface<T> current = theObject;
                while(current.getParent() != null) {
                    if (current.getParent().getRight() == current && current.getParent().getLeft() != null) {
                        DopInfo dip = dopInfo.get(current.getParent().getLeft());
                        result += dip.width;
                    }
//                    if (current.getParent().getRight() == current && current.getParent().getLeft() == null) {
//                        DopInfo dip = dopInfo.get(current.getParent());
//                        result += dip.width / 2;
//                    }
                    current = current.getParent();
                }
                return result;
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

        JButton jButtonSeek = new JButton("seek");
        jButtonSeek.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Long newValue = Long.parseLong(jTextField.getText());
                Boolean b = tree.seek((T)newValue, null);
                if (b) {
                    System.out.println("seek ok");
                } else {
                    System.out.println("seek fail");
                }
                jTextField.setText("");
                jTextField.requestFocusInWindow();
                dopInfo.clear();
                SwingUtilities.updateComponentTreeUI(frame);
            }
        });

        JButton jButtonAddRnd = new JButton("add rnd");
        jButtonAddRnd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int newValue = 1;
                try {
                    newValue = Integer.parseInt(jTextField.getText());
                } catch (Exception ex) {
                    newValue = 1;
                }
                Random r = new Random();
                for (int i = 0; i < newValue; i++) {
                    tree.add((T)(new Long(r.nextLong())));
                }
                jTextField.setText("");
                jTextField.requestFocusInWindow();
                dopInfo.clear();
                SwingUtilities.updateComponentTreeUI(frame);
            }
        });

        JButton jButtonDelRnd = new JButton("del rnd");
        jButtonDelRnd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int newValue = 1;
                try {
                    newValue = Integer.parseInt(jTextField.getText());
                } catch (Exception ex) {
                    newValue = 1;
                }
                Random r = new Random();
                while (newValue > 0) {
                    Integer ri = r.ints(0, tree.getSize().intValue())
                            .findFirst()
                            .getAsInt();

                    Iterator it = tree.iterator();
                    for (int i = 0; i < ri; i++) {
                        it.next();
                    }
                    it.remove();
                    newValue--;
                }
                jTextField.setText("");
                jTextField.requestFocusInWindow();
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
        jMenuBar.add(jButtonSeek);
        jMenuBar.add(jButtonAddRnd);
        jMenuBar.add(jButtonDelRnd);
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
        public Integer w;
        public Integer width;

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

        @Override
        public String toString() {
            return "DopInfo{" +
                    "currentNodeCount=" + currentNodeCount +
                    ", level=" + level +
                    ", x=" + x +
                    ", y=" + y +
                    ", w=" + width +
                    '}';
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
