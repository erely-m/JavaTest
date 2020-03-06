package com.erely.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class RBTree<T extends Comparable<T>> {
    private RBNode<T> root; //根节点

    private static final boolean RED = true; //红色
    private static final boolean BLACK = false; //黑色

    public class RBNode<T extends Comparable<T>> {

        private boolean color; //颜色
        private T key;
        private RBNode<T> leftNode; //左节点
        private RBNode<T> rightNode; //右节点
        private RBNode<T> parentNode; //父节点

        public RBNode(boolean color, T key, RBNode<T> leftNode, RBNode<T> rightNode, RBNode<T> parentNode) {
            this.color = color;
            this.key = key;
            this.leftNode = leftNode;
            this.rightNode = rightNode;
            this.parentNode = parentNode;
        }

        public boolean isColor() {
            return color;
        }

        public void setColor(boolean color) {
            this.color = color;
        }

        public T getKey() {
            return key;
        }

        public void setKey(T key) {
            this.key = key;
        }

        public RBNode<T> getLeftNode() {
            return leftNode;
        }

        public void setLeftNode(RBNode<T> leftNode) {
            this.leftNode = leftNode;
        }

        public RBNode<T> getRightNode() {
            return rightNode;
        }

        public void setRightNode(RBNode<T> rightNode) {
            this.rightNode = rightNode;
        }

        public RBNode<T> getParentNode() {
            return parentNode;
        }

        public void setParentNode(RBNode<T> parentNode) {
            this.parentNode = parentNode;
        }
    }

    public RBTree() {
        root = null;
    }

    public RBNode<T> getParentNode(RBNode<T> node) {
        if (node != null) {
            return node.getParentNode();
        } else {
            return null;
        }
    }

    public RBNode<T> getLeftNode(RBNode<T> node) {
        return node.getLeftNode();
    }

    public RBNode<T> getRightNode(RBNode<T> node) {
        return node.getRightNode();
    }

    public boolean isRed(RBNode<T> node) {
        return (node != null && node.isColor() == RED) ? true : false;
    }

    public boolean isBlack(RBNode<T> node) {
        return !isRed(node);
    }

    public void setRed(RBNode<T> node) {
        if (node != null) node.setColor(RED);
    }

    public void setBlack(RBNode<T> node) {
        if (node != null) node.setColor(BLACK);
    }

    //根据key进行数据查找
    public RBNode<T> find4Key(T key, RBNode<T> node) {
        if (node != null) {
            int result = key.compareTo(node.getKey());
            if (result < 0) {//遍历左节点
                return find4Key(key, node.getLeftNode());
            } else if (result > 0) {//遍历右节点
                return find4Key(key, node.getRightNode());
            } else {
                return node;
            }
        } else {
            return null;
        }
    }

    //查找左叶子节点，如果为左孩子则是最小值，如果为右孩子，则是右孩子最小值也就是大于该节点的最小值
    public RBNode<T> findMin4Left(RBNode<T> node) {
        if (node.getLeftNode() == null) {
            return node;
        } else {
            return findMin4Left(node.getLeftNode());
        }
    }

    //查找右叶子节点
    public RBNode<T> findMax4Right(RBNode<T> node) {
        if (node.getRightNode() == null) {
            return node;
        } else {
            return findMax4Right(node);
        }
    }

    //查找删除节点的替代节点，红黑数中大于该节点的最小节点
    public RBNode<T> successor(RBNode<T> node) {
        if (node.getRightNode() != null) {
            return findMin4Left(node.getRightNode());
        }
        //该节点如果是父亲的左孩子，则父节点就是直接为替代节点,
        //如果该节点是父亲的右孩子
        RBNode<T> parent = node.getParentNode();
        while (parent != null && (parent.getRightNode() == node)) {
            node = parent;
            parent = parent.getParentNode();
        }

        return parent;
    }

    //节点左旋，当前节点作为旋转点(父节点)，该节点左孩子变为原父节点右孩子
    public void leftRotate(RBNode<T> node) {
        RBNode<T> rightNode = node.getRightNode();
        if (rightNode.getLeftNode() != null) { //如果左孩子不等于空
            rightNode.getLeftNode().setParentNode(node); //设置父节点为当前孩子
        }
        node.rightNode = rightNode.getLeftNode();
        rightNode.setLeftNode(node);
        rightNode.setParentNode(node.parentNode);//右孩子父亲变成祖父

        if (node.getParentNode() != null) {
            if (node.getParentNode().getLeftNode() == node) {
                //如果祖父的左孩子为当前节点则将选中后设置为左孩子
                node.getParentNode().setLeftNode(rightNode);
            } else {
                node.getParentNode().setRightNode(rightNode);
            }
        } else {
            this.root = rightNode;
        }
        //最后将原父节点父亲指向当前节点
        node.setParentNode(rightNode);
    }

    //节点右旋，当前节点为旋转点，该节点右孩子变为父节点左孩子
    public void rightRotate(RBNode<T> node) {
        RBNode<T> leftNode = node.getLeftNode();

        if (leftNode.getRightNode() != null) {
            leftNode.getRightNode().setParentNode(node);

        }
        leftNode.setParentNode(node.getParentNode());
        node.setLeftNode(leftNode.getRightNode());
        leftNode.setRightNode(node);

        if (node.getParentNode() != null) {
            if (node.getParentNode().getLeftNode() == node) {
                node.getParentNode().setLeftNode(leftNode);
            } else {
                node.getParentNode().setRightNode(leftNode);
            }
        } else {
            this.root = leftNode;

        }
        node.setParentNode(leftNode);
    }

    /**
     * 插入后红黑树自动平衡
     *
     * @param node 新插入节点
     */
    public void balanceInsertion(RBNode<T> node) {

        RBNode<T> parent, gParent;
        /**
         * 插入节点为红色，当父节点为黑色时直接插入即可，如果父亲节点为红色，则要分多种情况讨论
         */
        while ((parent = node.getParentNode()) != null && isRed(parent)) {//存在父节点且父节点为红色
            //拿到祖父节点，需要通过祖父节点获取叔叔节点
            gParent = parent.getParentNode(); //父节点为红色就一定能拿到祖父节点，因为根节点必须为黑
            //判断父节点是祖父的节点的左孩子，还是右孩子
            if (gParent.getLeftNode() == parent) {
                //如果父节点是左孩子 则说明叔叔节点为右孩子
                RBNode<T> uncle = gParent.getRightNode();
                //对叔叔节点进行进一步分析，分析叔叔节点是否为红色，
                if (uncle != null && isRed(uncle)) {
                    System.out.println("原始颜色");
                    printTreeLevel();
                    //此处需要进行变色将叔叔和父亲变成黑色，祖父变成红色
                    setBlack(uncle);
                    setBlack(parent);
                    setRed(gParent);
                    node = gParent;//将指针指向祖父节点，对祖父节点进行判断是否需要进行处理
                    System.out.println("变色完成");
                    printTreeLevel();
                    continue;
                } else {//叔叔节点为黑色或者不存在 父亲为红色
                    if (parent.getRightNode() == node) {
                        //如果当前节点是父节点的右孩子需要对当前节点进行一次左旋
                        leftRotate(parent); //此时已经进行了交换需要将孩子和父亲进行交换
                        RBNode<T> temp = node;
                        node = parent;
                        parent = temp;
                    }
                    /**
                     * 上面处理完毕之后当前节点为红色，父节点为黑色，此时不满足条件5祖父节点需要变红
                     */
                    setRed(gParent);
                    setBlack(parent);
                    /**
                     * 父节点进行一次右旋 过程
                     *
                     *         黑祖               黑祖
                     *        /    \             /   \
                     *     红父    黑叔  -->  红插   黑叔
                     *        \               /
                     *        红插          红父
                     *     可以看到上面，经过了一次旋转之后，平衡性仍然满足，但是颜色就不对了，仍然存在双红情况
                     *     其实理解了AVL树的平衡调整，这里其实也是一个“双旋转”的过程，只有被删节点与父亲不同侧时才需要双旋
                     *     继续接着上面的双红情况，因为颜色不对，这时需要变色
                     *                黑祖          红祖
                     *               /   \          /   \
                     *            红插   黑叔 --> 黑插  黑叔
                     *           /                /
                     *        红父              红父
                     *      可以看到，变色之后，右边的子树和左边子树的黑色节点个数保持一致，明显和原来不同，原来的图，看左上角，右子树的黑色节点要多一个
                     *      原来的树肯定是满足红黑树性质的，所以这里只需要做一次简单的右旋，就可以让右子树的黑色节点个数再次比左边多一个，满足了原来的情况
                     *
                     */
                    rightRotate(gParent); //进行一次右旋处理完毕 此时对祖父进行右旋实际上还是对当前节点进行右旋
                }
            } else { //如果插入节点父节点是祖父节点的右孩子
                RBNode<T> uncle = gParent.getLeftNode(); //获取叔叔节点
                //如果叔叔节点为红色则需要变色
                if (uncle != null && isRed(uncle)) {
                    setBlack(uncle);
                    setBlack(parent);
                    setRed(gParent);
                    node = gParent;
                    continue;
                } else {
                    //如果当前节点为左孩子 则需要进行孩子右旋 右旋完成交换
                    if (parent.leftNode == node) {
                        rightRotate(parent);
                        RBNode<T> temp = node;
                        node = parent;
                        parent = temp;
                    }
                    //进行变色
                    setBlack(parent);
                    setRed(gParent);
                    //变色完成左旋
                    leftRotate(gParent);
                }
            }
        }
        //调整完毕，如果根节点为当前节点需要变为黑色
        if (root == node) {
            setBlack(root);
        }
    }

    /**
     * 删除后自平衡，将替换节点通过节点平衡调节到叶子节点（非null）然后对其转为叶子节点的删除直接删除即可，对替换节点进行操作也就是寻找后继大于该值的最小值进行替换
     * 进入该方法说明被删除节点为黑色
     */
    public void balanceDelete(RBNode<T> node, RBNode<T> parent) {
        RBNode<T> other;//父节点的另外一个子节点
        while (isBlack(node) && node != this.root) {//如果替代节点也为黑色则需要进行调整
            if (parent.getLeftNode() == node) { //如果替代节点是父节点的左节点
                other = parent.getRightNode(); //取到兄弟节点
                if (isRed(other)) { //兄弟节点为红色则需要进行调整 先变色然后左旋
                    setRed(parent);//父节点变红
                    setBlack(node);//当前节点变黑
                    leftRotate(parent); //左旋
                    continue;
                } else {//替换节点为黑节点时无法确认父节点颜色，需要考虑多种情况
                    if (isBlack(other.getRightNode()) && isBlack(other.getLeftNode())) {//两个孩子都是黑色节点，空节点也是黑色
                        //该情形说明孩子节点不能进行节点替换，把当前兄弟节点变黑，替代节点为父节点，借用父节点进行处理
                        setRed(other);
                        node = parent;
                        parent = parent.getParentNode();
                    } else if (isRed(other.getLeftNode()) && isBlack(other.getRightNode())) {//左节点红色，右节点黑色
                        setRed(other);
                        setBlack(other.getLeftNode());
                        rightRotate(other);
                    } else if (isRed(other.getRightNode())) { //右节点为红色
                        other.setColor(parent.isColor()); //节点设置为父节点颜色
                        setBlack(parent); //父节点设置黑
                        setBlack(other.getRightNode());//右节点变黑
                        leftRotate(parent); //左旋
                        break;
                    }
                }
            } else { //兄弟节点为左节点
                other = parent.getLeftNode();
                if (isRed(other)) { //兄弟节点为红色则需要进行调整 先变色然后右旋
                    setRed(parent);//父节点变红
                    setBlack(node);//当前节点变黑
                    rightRotate(parent);
                } else {//替换节点为黑节点时无法确认父节点颜色，需要考虑多种情况
                    if (isBlack(other.getRightNode()) && isBlack(other.getLeftNode())) {//两个孩子都是黑色节点，空节点也是黑色
                        //该情形说明孩子节点不能进行节点替换，把当前兄弟节点变黑，替代节点为父节点，借用父节点进行处理
                        setRed(other);
                        node = parent;
                        parent = parent.getParentNode();
                    } else if (isRed(other.getLeftNode()) && isBlack(other.getRightNode())) {//左节点红色，右节点黑色
                        setRed(other);
                        setBlack(other.getLeftNode());
                        leftRotate(other);
                    } else if (isRed(other.getRightNode())) { //右节点为红色
                        other.setColor(parent.isColor()); //节点设置为父节点颜色
                        setBlack(parent); //父节点设置黑
                        setBlack(other.getRightNode());//右节点变黑
                        rightRotate(parent);
                        break;
                    }
                }
            }
        }
        if (node == this.root) {
            setBlack(node);
        }
    }

    /**
     * 删除操作
     *
     * @param node
     */
    public void delete(RBNode<T> node) {
        RBNode<T> child, parent, replace;
        Boolean color = true;
        //如果待删除节点存在左右孩子
        if (node.getLeftNode() != null && node.getRightNode() != null) {
            replace = successor(node);//查找替代节点
            parent = replace.getParentNode();//替代节点的父节点
            child = replace.getRightNode(); //获取右孩子此时只有有孩子因为替换节点已经是最小的了
            color = replace.isColor();
            if (node == replace.getParentNode()) { //如果替换节点是当前节点的孩子则直接删除即可
                parent = replace;
            } else {
                if (child != null) { //如果替换节点存在右孩子，如果直接替换则会丢失该节点，需要将该节点执行父节点
                    child.setParentNode(parent);
                }
                //对节点进行处理
                parent.setLeftNode(child);
                replace.setRightNode(node.getRightNode());
                node.getRightNode().setParentNode(replace);
            }
            //将目标节点和替换节点进行交换
            replace.setParentNode(node.getParentNode());
            replace.setLeftNode(node.getLeftNode());
            node.getLeftNode().setParentNode(replace);
            //颜色覆盖
            replace.setColor(node.isColor());
            if (node.getParentNode() != null) {//如果目标节点的父亲不为空则要将父亲指针指向替换节点
                if (node.getParentNode().getLeftNode() == node) {
                    node.getParentNode().setLeftNode(replace);
                } else {
                    node.getParentNode().setRightNode(replace);
                }
            } else { //说明节点为根节点
                this.root = replace;
            }
            //节点替换完成就可以开始删除了，如果为红色直接删除就可以
            if (color == BLACK) { //此处节点已经被删除了，及原替代节点的孩子直接就是替代节点父亲的孩子
                balanceDelete(child, parent);
            }
        } else {//只有左孩子或者右孩子或者没有孩子
            if (node.getLeftNode() != null) {
                replace = node.getLeftNode(); //孩子就是替换节点
            } else {
                replace = node.getRightNode();
            }
            parent = node.getParentNode(); //获取父节点
            if (parent == null) {//为跟节点
                this.root = replace;
            } else {//处理父亲孩子指针
                if (node == parent.getLeftNode()) {
                    parent.setLeftNode(replace);
                } else {
                    parent.setRightNode(replace);
                }
            }
            if(replace == null){ //如果没有孩子节点直接删除就ok
                return;
            }
            //设置替代节点父亲
            replace.setParentNode(node.getParentNode());
            color = node.isColor();
            child = replace;
            if (color == BLACK) {
                balanceDelete(child, parent); //平衡处理
            }
        }
        printTreeLevel();
    }

    /**
     * 插入节点
     *
     * @param key
     */
    public void insert(T key) {

        RBNode<T> insertNode = this.root;
        RBNode<T> parent = null;
        while (insertNode != null) {
            parent = insertNode; //记录父节点
            int result = key.compareTo(insertNode.getKey());
            if (result == 0) { //存在相同节点直接更新值
                insertNode.setKey(key);
                return;
            }

            if (result < 0) {
                insertNode = insertNode.getLeftNode();
            } else {
                insertNode = insertNode.getRightNode();
            }
        }
        RBNode<T> newNode = new RBNode<T>(BLACK, key, null, null, parent);
        if (parent != null) {
            if (key.compareTo(parent.getKey()) < 0) { //判断该节点是哪个孩子节点
                parent.setLeftNode(newNode);
            } else {
                parent.setRightNode(newNode);
            }
        } else {
            this.root = newNode;
        }
        setRed(newNode);//新插入节点颜色为红色
        balanceInsertion(newNode); //平衡
    }

    //前中后遍历
    public void preOrder(RBNode<T> node) {
        if (node != null) {
            System.out.println(node.getKey());
            preOrder(node.getLeftNode());
            preOrder(node.getRightNode());
        }
    }

    public void midOrder(RBNode<T> node) {
        if (node != null) {
            midOrder(node.getLeftNode());
            System.out.println(node.getKey());
            midOrder(node.getRightNode());
        }
    }

    public void afterOrder(RBNode<T> node) {
        if (node != null) {
            afterOrder(node.getLeftNode());
            afterOrder(node.getRightNode());
            System.out.println(node.getKey());
        }
    }

    public void printTreeLevel() {

        System.out.println("开始输出树的层级结构");
        ConcurrentHashMap<Integer, List<RBNode>> map = showTree();
        int size = map.size();

        for (int i = 0; i < map.size(); i++) {
            System.out.println();
            for (int j = 0; j < map.get(i).size(); j++) {
                System.out.print(makeSpace2(size, i) +
                        (map.get(i).get(j).key == null ? " " : (map.get(i).get(j).key) + (map.get(i).get(j).color ? "(红)" : "(黑)")) + makeSpace2(size, i));

            }
            System.out.println();
        }
        System.out.println("结束输出树的层级结构");

    }

    public void printTreeLevel2() {

        System.out.println("开始输出树的Graphviz结构");
        ConcurrentHashMap<Integer, List<RBNode>> map = showTree();
        int size = map.size();
        System.out.println("digraph kunghsu{");
        for (int i = 0; i < map.size(); i++) {
            for (int j = 0; j < map.get(i).size(); j++) {

                if (map.get(i).get(j).key != null) {
                    System.out.println(map.get(i).get(j).key + " [color=" + (map.get(i).get(j).color == RED ? "red" : "black") + " style=filled fontcolor=white] ");
                }
            }
        }

        for (int i = 0; i < map.size(); i++) {
            for (int j = 0; j < map.get(i).size(); j++) {
                String content = "";

//                if (map.get(i).get(j).key != null) {
//                    if (map.get(i).get(j).getLeftNode() != null) {
//                        System.out.println(map.get(i).get(j).key + "->" + map.get(i).get(j).getLeftNode().getKey() + "[label=left]");
//                    }
//                    if (map.get(i).get(j).getRightNode() != null) {
//                        System.out.println(map.get(i).get(j).key + "->" + map.get(i).get(j).getLeftNode().getKey() + "[label=right]");
//                    }
//                }
            }
        }
        System.out.println("}");

        System.out.println("结束输出树的Graphviz结构");

    }

    /**
     * 为了让输出更有结构感，在元素前拼接一些空格，对齐
     *
     * @param size
     * @param index
     * @return
     */
    public String makeSpace2(int size, int index) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < 1 << (size - index); i++) {
            builder.append("  ");
        }
        return builder.toString();
    }

    public ConcurrentHashMap<Integer, List<RBNode>> showTree() {

        ConcurrentHashMap<Integer, List<RBNode>> map = new ConcurrentHashMap<Integer, List<RBNode>>();
        showTree(root, 0, map);
        return map;
    }

    public void showTree(RBNode root, int count, ConcurrentHashMap<Integer, List<RBNode>> map) {

        if (map.get(count) == null) {
            map.put(count, new ArrayList<RBNode>());
        }
        map.get(count).add(root);

        if (root.getLeftNode() != null) {
            showTree(root.getLeftNode(), count + 1, map);
        } else {
            //假如为空，也添加到map中，因为我要做格式化控制，空的，我也要知道它这个位置是空的
            if (map.get(count + 1) == null) {
                map.put(count + 1, new ArrayList<RBNode>());
            }
            map.get(count + 1).add(new RBNode(false, null, null, null, null));
        }
        if (root.getRightNode() != null) {
            showTree(root.getRightNode(), count + 1, map);
        } else {
            if (map.get(count + 1) == null) {
                map.put(count + 1, new ArrayList<RBNode>());
            }
            map.get(count + 1).add(new RBNode(false, null, null, null, null));
        }
    }

    public void delete(T t) {
        RBNode node;
        if ((node = find4Key(t, this.root)) != null) {
            System.out.println("删除节点前" + node.getKey());
            printTreeLevel();
            delete(node);
            System.out.println("删除节点" + node.getKey());
            printTreeLevel();
        }
    }

    public static void main(String[] args) {
        RBTree<Integer> rb = new RBTree<Integer>();
        for (int i = 0; i < 15; i++) {
            int a = (int) (Math.random() * 15 % 15);
            rb.insert(a);
        }
        rb.printTreeLevel();
        for (int i = 0; i < 15; i++) {
            int a = (int) (Math.random() * 15 % 15);
            rb.delete(a);
        }
//        System.out.println("中序");
//        rb.midOrder(rb.root);
//        rb.printTreeLevel();
    }
}
