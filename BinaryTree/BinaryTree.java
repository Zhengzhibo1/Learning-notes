// ================================================
// 构建二叉树类，类中包含根节点
// 结合TreeNode.java实现了二叉树基本功能：
// 1、添加节点
// 2、查询节点
// 3、删除节点
// 4、二叉树的深度优先遍历与宽度优先遍历
// ===================代码实现======================
package binTree;

import java.util.LinkedList;
import java.util.Queue;

//二叉树类
public class BinaryTree {

	TreeNode root;
	
//=========================添加节点================================
	/*
	 * 从root节点开始，遵循下面规则：
	 * 如果新节点值小于当前的节点值，将会进入左子树
	 * 如果新节点值大于当前的节点值，将会进入右子树
	 * 当当前的节点是null时，已经到达叶子节点，可以添加新节点到这个位置。
	 * 递归方法做添加节点操作
	*/
	private TreeNode addNode(TreeNode curNode, int value) {
		
		if(curNode == null) {
			return new TreeNode(value);
		}
		if(value < curNode.value) {
			curNode.left = addNode(curNode.left, value);
		}else if(value > curNode.value) {
			curNode.right = addNode(curNode.right, value);
		}else {
			return curNode;
		}
		return curNode;
	}
	
	public void addNode(int value) {
		root = addNode(root, value);
	}
	
//=========================查询节点================================
	public boolean isContainNode(TreeNode curNode, int value) {
		
		if(curNode == null) {
			return false;
		}else if(value == curNode.value) {
			return true;
		}else if(value > curNode.value) {
			 return isContainNode(curNode.right, value);
		}else {
			return isContainNode(curNode.left, value);
		}

	}
	
	public boolean isContainNode(int value) {
		return isContainNode(root, value);
	}
	
//=========================删除节点================================
	/*二叉树删除节点规则：
	 * 1、删除的节点为叶子节点，直接删除；
	 * 2、删除的节点只存在左子树或者右子树，删除节点的父节点直接指向子树节点；
	 * 3、删除的节点同时存在左子树和右子树，
	 * 将删除节点的左子树的最右节点(左子树最大值)或者右子树的最左节点(右子树的最小值)替换删除节点，
	 * 同时删除替换节点，再将删除节点指向子树节点。
	  */
	
	public TreeNode deleteNode(TreeNode curNode, int value) {
		
		if(curNode == null) {
			return null;
		}
		if(value == curNode.value) {
			if(curNode.left == null && curNode.right == null) {
				return null;
			}else if(curNode.left == null && curNode.right != null) {
				return curNode.right;
			}else if(curNode.right == null && curNode.left != null) {
				return curNode.left;
			}else {
				//用右子树的最小值替换要删除节点，然后删除掉右节点最小值
				int rightSmallestValue = findSmallestValue(curNode.right);
				curNode.value = rightSmallestValue;
				curNode.right = deleteNode(curNode.right, rightSmallestValue);
				return curNode;
			}			
		}else if(value < curNode.value) {
			curNode.left = deleteNode(curNode.left, value);
		}else if(value > curNode.value) {
			curNode.right = deleteNode(curNode.right, value);
		}
				
		return curNode;
	}
	
	private int findSmallestValue(TreeNode root) {
		return root.left == null ? root.value : findSmallestValue(root.left);
	}
	
//=========================遍历树================================	
	/*
	 * 遍历方式：深度优先(depth-first)、宽度优先(breadth-first)
	 * 深度优先:前序遍历、中序遍历、后续遍历
	 * */
	
	//前序遍历:递归实现
	public void preOrder(TreeNode root) {
		if(root != null) {
			System.out.print(root.value + " ");
			preOrder(root.left);
			preOrder(root.right);
		}
	}
	
	//中序遍历:递归实现
	public void inOrder(TreeNode root) {
		if(root != null) {			
			inOrder(root.left);
			System.out.print(root.value + " ");
			inOrder(root.right);
		}
	}
	
	//后序遍历:递归实现
	public void postOrder(TreeNode root) {
		if(root != null) {			
			inOrder(root.left);
			inOrder(root.right);
			System.out.print(root.value + " ");
		}
	}
	
	//宽度优先遍历
	/*遍历下一级节点前，会遍历当前级的所有节点
	 * 用队列储存节点,先进先出
	 * */
	public void levelOrder(TreeNode root) {
		if(root != null) {
			Queue<TreeNode> nodes = new LinkedList<TreeNode>();
			nodes.add(root);
			while(!nodes.isEmpty()) {
				TreeNode node = nodes.remove();
				System.out.print(node.value + " ");
				if(node.left != null) {
					nodes.add(node.left);
				}
				if(node.right != null) {
					nodes.add(node.right);
				}
			}
		}
	}
	
	
}
