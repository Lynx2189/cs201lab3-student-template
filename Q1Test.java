public class Q1Test {
     public static void main(String[] args) {

          String result, traversal1, traversal2, traversal3;

          traversal1 = "1-2";
          traversal2 = "2-1-3";
          traversal3 = "1-3-2";
          result = verify(traversal1, traversal2, traversal3);
          System.out.println("Traversal 1 : " + traversal1);
          System.out.println("Traversal 2 : " + traversal2);
          System.out.println("Traversal 3 : " + traversal3);
          System.out.println("Expected Result : Invalid traversals");
          System.out.println("  Actual Result : " + result);
          System.out.println();

          traversal1 = "1-2-3";
          traversal2 = "2-1-3";
          traversal3 = "1-3-2";
          result = verify(traversal1, traversal2, traversal3);
          System.out.println("Traversal 1 : " + traversal1);
          System.out.println("Traversal 2 : " + traversal2);
          System.out.println("Traversal 3 : " + traversal3);
          System.out.println(
                    "Expected Result : Traversal 1 - Inorder, Traversal 2 - Preorder, Traversal 3 - Postorder");
          System.out.println("  Actual Result : " + result);
          System.out.println();

          traversal1 = "1-2-3";
          traversal2 = "3-2-1";
          traversal3 = "2-3-1";
          result = verify(traversal1, traversal2, traversal3);
          System.out.println("Traversal 1 : " + traversal1);
          System.out.println("Traversal 2 : " + traversal2);
          System.out.println("Traversal 3 : " + traversal3);
          System.out.println("Expected Result : Invalid traversals");
          System.out.println("  Actual Result : " + result);
          System.out.println();

          traversal1 = "3-1-2-5-4";
          traversal2 = "2-1-4-5-3";
          traversal3 = "1-2-3-4-5";
          result = verify(traversal1, traversal2, traversal3);
          System.out.println("Traversal 1 : " + traversal1);
          System.out.println("Traversal 2 : " + traversal2);
          System.out.println("Traversal 3 : " + traversal3);
          System.out.println(
                    "Expected Result : Traversal 1 - Preorder, Traversal 2 - Postorder, Traversal 3 - Inorder");
          System.out.println("  Actual Result : " + result);
          System.out.println();

          traversal1 = "10-20-30-40-50";
          traversal2 = "20-10-30-40-50";
          traversal3 = "10-50-40-30-20";
          result = verify(traversal1, traversal2, traversal3);
          System.out.println("Traversal 1 : " + traversal1);
          System.out.println("Traversal 2 : " + traversal2);
          System.out.println("Traversal 3 : " + traversal3);
          System.out.println(
                    "Expected Result : Traversal 1 - Inorder, Traversal 2 - Preorder, Traversal 3 - Postorder");
          System.out.println("  Actual Result : " + result);
          System.out.println();
     } 

      // write your codes here
     public static String verify(String traversal1, String traversal2, String traversal3) {

          int[][] traversals = {parseTraversal(traversal1), parseTraversal(traversal2), parseTraversal(traversal3)};
     
          if (traversals[0] == null || traversals[1] == null || traversals[2] == null) {
               return "Invalid traversals";
          }

          int size = traversals[0].length;

          if (traversals[1].length != size || traversals[2].length != size) {
               return "Invalid traversals";
          }
     
          for (int in = 0; in < 3; in++) {
               if (!isIncreasing(traversals[in])) {
                    continue;
               }
     
               for (int pre = 0; pre < 3; pre++) {
                    if (pre == in) {
                         continue;
                    }
                    int post = 3 - in - pre;
                    if (matchesTraversals(traversals[in], traversals[pre], traversals[post], 0, 0, 0, size)) {
                         String[] orders = new String[3];
                         orders[in] = "Inorder";
                         orders[pre] = "Preorder";
                         orders[post] = "Postorder";
                         return "Traversal 1 - " + orders[0] + ", Traversal 2 - " + orders[1] + ", Traversal 3 - " + orders[2];
                    }
               }
          }
          return "Invalid traversals";
     }
     
     private static int[] parseTraversal(String traversal) {
          if (traversal == null || traversal.trim().isEmpty()) {
               return null;
          }
     
          String[] values = traversal.trim().split("(?<=\\d)-", -1);
          int[] nodes = new int[values.length];
          try {
               for (int i = 0; i < values.length; i++) {
                    nodes[i] = Integer.parseInt(values[i].trim());
               }
          } catch (NumberFormatException e) {
               return null;
          }
          return nodes;
     }
     
     private static boolean isIncreasing(int[] nodes) {
          for (int i = 1; i < nodes.length; i++) {
               if (nodes[i - 1] >= nodes[i]) {
                    return false;
               }
          }
          return true;
     }
     
     private static boolean matchesTraversals(int[] inorder, int[] preorder, int[] postorder, int inStart, int preStart, int postStart, int size) {
          if (size == 0) {
               return true;
          }
     
          int root = preorder[preStart];
          if (postorder[postStart + size - 1] != root) {
               return false;
          }
     
          int rootIndex = inStart;
          while (rootIndex < inStart + size && inorder[rootIndex] != root) {
               rootIndex++;
          }
          if (rootIndex == inStart + size) {
               return false;
          }

          int leftSize = rootIndex - inStart;
          int rightSize = size - leftSize - 1;
     
          return matchesTraversals(inorder, preorder, postorder, inStart, preStart + 1, postStart, leftSize) 
          && matchesTraversals(inorder, preorder, postorder, rootIndex + 1, preStart + leftSize + 1, postStart + leftSize, rightSize);
     }
}
