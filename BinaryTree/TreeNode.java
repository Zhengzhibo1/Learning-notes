// ============================================================
// 学习二叉树的基本结构
// 实现简单二叉树
// 完成了二叉树的3种遍历（前序遍历、中序遍历、后序遍历）的递归与非递归实现
// ======================代码实现部分==========================
package binTree;

import java.util.Stack;

//树节点类
public class TreeNode {

	public int value;
	public TreeNode left;
	public TreeNode right;
	
	//构造函数
	public TreeNode(int value) {
		this.value = value;
		this.left = null;
		this.right = null;
	}

	/*前序遍历：
	 * 根左右
	 * 若二叉树为空则结束返回，否则：
	 * 1、访问根节点
	 * 2、前序遍历左子树
	 * 3、前序遍历右子树
	 * */
	
	//前序遍历:递归实现
	public static void preOrderRe(TreeNode root) {
		if(root != null) {
			System.out.print(root.value + " ");
			preOrderRe(root.left);
			preOrderRe(root.right);
		}
	}
	
	//前序遍历：非递归实现
	public static void preOreder(TreeNode root) {
		Stack<TreeNode> stack = new Stack<TreeNode>();
		while(root != null || !stack.isEmpty()) {
			while(root != null) {
				System.out.print(root.value + " ");
				stack.push(root);
				root = root.left;
			}
			if(!stack.isEmpty()) {
				root = stack.pop();
				root = root.right;
			}
		}
	}
	
	/*中序遍历
	 * 左根右
	 * 若二叉树为空则结束返回，否则：
	 * 1、中序遍历左子树
	 * 2、访问跟节点
	 * 3、中序遍历右子树
	 * */
	
	//中序遍历:递归实现
	public static void inOrderRe(TreeNode root) {
		if(root != null) {			
			inOrderRe(root.left);
			System.out.print(root.value + " ");
			inOrderRe(root.right);
		}
	}
	
	//中序遍历：非递归实现
	public static void inOreder(TreeNode root) {
		Stack<TreeNode> stack = new Stack<TreeNode>();
		while(root != null || !stack.isEmpty()) {
			while(root != null) {
				stack.push(root);
				root = root.left;
			}
			
			if(!stack.isEmpty()) {
				root = stack.pop();
				System.out.print(root.value + " ");
				root = root.right;
			}
		}
	}
	
	/*后序遍历
	 * 左右根
	 * 若二叉树为空则结束返回，否则：
	 * 1、后序遍历左子树
	 * 2、后序遍历右子树
	 * 3、访问根节点
	 * 非递归算法核心：
	 * 添加辅助栈，以便知道栈回退时是从左子节点返回到根节点还是从右子节点返回根节点
	 * 如果从左子节点返回根节点，此时就应该去访问右子节点，
	 * 如果从右子节点返回根节点，此时就应该去访问根节点
	 * */
	
	//后序遍历:递归实现
	public static void postOrderRe(TreeNode root) {
		if(root != null) {			
			postOrderRe(root.left);
			postOrderRe(root.right);
			System.out.print(root.value + " ");
		}
	}
	
	//后序遍历：非递归实现
	public static void postOreder(TreeNode root) {
		int left = 1; // 在辅助栈里表示左节点
		int right = 2; // 在辅助栈里表示右节点
		Stack<TreeNode> stack = new Stack<TreeNode>();
		Stack<Integer> stack2 = new Stack<Integer>();
		
		while(root != null || !stack.isEmpty()) {
			while(root != null) {
				stack.push(root);
				stack2.push(left);
				root = root.left;
			}
			
			while(!stack.isEmpty() && stack2.peek() == right) {
				stack2.pop();
				System.out.print(stack.pop().value + " ");
			}
			
			if(!stack.isEmpty() && stack2.peek() == left) {
				stack2.pop();
				stack2.push(right);
				root = stack.peek().right;
			}
		}
	}
	
}
