import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Main {

    //Bu metod, verilen dosya yolundan sayıları okuyarak bir İkili Arama Ağacı (BST) oluşturur,
    // ağacın AVL ağacı olup olmadığını kontrol eder ve istatistikleri yazdırır.
    public static void Question_1(String filePath) throws IOException {
        //Okunacak satırı tutan bir değişken tanımlıyoruz.
        String line = "";

        //Dosyayı okumak için BufferedReader nesnesi oluşturuyoruz.
        BufferedReader br = new BufferedReader(new FileReader(filePath));
        BinarySearchTree bst = new BinarySearchTree();

        //Dosyadan okunan her satırı (sayıyı) ağaca ekliyoruz.
        while ((line = br.readLine()) != null){
            bst.insert(Integer.parseInt(line));
        }
        //Ağacın AVL ağacı olup olmadığını kontrol ediyoruz ve istatistikleri yazdırıyoruz.
        bst.isAVL();
        bst.printStats();
    }
    //Bu metod, iki dosya yolu alarak bir ana BST ve bir alt ağaç listesi oluşturur,
    //ardından iki anahtar değerinin yolunu yazdırır ve alt ağacın ana ağacın bir alt ağacı olup olmadığını kontrol eder.
    public static void Question_2(String treeFilePath, String subTreeFilePath) throws IOException {

        String line = "";

        BufferedReader br = new BufferedReader(new FileReader(treeFilePath));
        BinarySearchTree bst = new BinarySearchTree();

        while ((line = br.readLine()) != null){
            bst.insert(Integer.parseInt(line));
        }

        bst.searchWithPath(7);
        bst.searchWithPath(2);


        br = new BufferedReader(new FileReader(subTreeFilePath));
        ArrayList<Integer> subtreeList = new ArrayList<>();

        while ((line = br.readLine()) != null){
            subtreeList.add(Integer.parseInt(line));
        }

        bst.searchSubtree(subtreeList);
    }

    public static void main(String[] args) throws IOException {
        Question_1("src\\treecheck.txt");
        Question_2("src\\tree_1.txt", "src\\subtree.txt");
        /*BinarySearchTree bst = new BinarySearchTree();
        bst.insert(5);
        bst.insert(3);
        bst.insert(17);
        bst.insert(9);
        bst.insert(23);
        bst.insert(54);
        bst.insert(11);
        bst.insert(79);
        bst.insert(30);
        bst.insert(12);

        bst.isAVL();
        bst.printStats();

        BinarySearchTree bst_2 = new BinarySearchTree();
        bst_2.insert(5);
        bst_2.insert(3);
        bst_2.insert(8);
        bst_2.insert(7);
        bst_2.insert(22);
        bst_2.insert(2);

        ArrayList<Integer> subtreeList = new ArrayList<>();
        subtreeList.add(5);
        subtreeList.add(7);

        bst_2.searchSubtree(subtreeList);*/

    }
}
