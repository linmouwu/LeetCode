public class Codec {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if(root == null){
            return "null,";
        }
        StringBuffer sb = new StringBuffer();
        sb.append(root.val);
        sb.append(',');
        sb.append(serialize(root.left));
        sb.append(serialize(root.right));
        return sb.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if(data == null || data.equals("null,")){
            return null;
        }
        String[] parts = data.split(",");
        if(parts[0].equals("null")){
            return null;
        }
        int rootVal = Integer.valueOf(parts[0]);
        TreeNode root = new TreeNode(rootVal);
        int index = -1;
        for(int i = 1; i < parts.length;i++){
            if(!parts[i].equals("null") && Integer.valueOf(parts[i]) > rootVal){
                index = data.indexOf(parts[i]);
                break;
            }
        }
        if(index == -1){
            root.left = deserialize(data.substring(data.indexOf(",") + 1));
            root.right = null;
        }else{
            root.left = deserialize(data.substring(data.indexOf(",") + 1, index));
            root.right = deserialize(data.substring(index));
        }
        return root;
    }



    public static void main(String[] args){
        Codec codec = new Codec();
        TreeNode one = new TreeNode(6);
        TreeNode two = new TreeNode(4);
        TreeNode three = new TreeNode(17);
//        TreeNode four = new TreeNode();
        TreeNode five = new TreeNode(2);
        TreeNode six = new TreeNode(0);
        TreeNode seven = new TreeNode(8);
        TreeNode eight = new TreeNode(7);
        TreeNode nine = new TreeNode(4);

        one.left = two;
        one.right = three;


        String result = codec.serialize(one);
        System.out.println();
        TreeNode root = codec.deserialize(result);
        System.out.println(root.val);
    }
}