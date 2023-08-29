import java.util.ArrayList;

public class BinarySearchTree {

    //İkili Arama Ağacının (BST) kök düğümünü temsil eder.
    private Node root;
    //Ağaçtaki istatistikleri (maksimum, minimum, ortalama) tutan BSTStats nesnesi.
    private BSTStats stats;
    //Anahtar değeri arama işlemi???????????????
    private String findKeyPath;

    //Kurucu fonksiyon, kök düğümü ve istatistik nesnesini başlatır.
    public BinarySearchTree(){
        root = null;
        stats = new BSTStats();
    }
    //Key ve root degeri nedir??????????????????????????????

    //Bu fonksiyon, yeni bir düğümü ağaca eklemek için kullanılır.
    //Ağacın kök düğümünü (root), insert(Node node, int key) fonksiyonunu çağırarak günceller.
    public void insert(int key) {
        root = insert(root, key);
    }
    //Bu fonksiyon, yeni bir düğümü uygun konuma eklemek için kullanılır.
    //Eğer geçerli düğüm (node) null ise, yani bu konumda henüz bir düğüm yoksa, yeni bir düğüm oluşturulur ve bu düğüm döndürülür.
    //Bu durum, yeni düğümün ekleneceği yerin bulunduğu anlamına gelir.
    //Eğer eklemek istediğimiz anahtar değeri (key) geçerli düğümün anahtar değerinden küçükse, geçerli düğümün sol çocuğunu güncelleriz.
    //Sol çocuğa, yine aynı "insert" fonksiyonunu çağırarak sol alt ağaca eklemeyi sürdürürüz.
    //Eğer eklemek istediğimiz anahtar değeri (key) geçerli düğümün anahtar değerinden büyükse, geçerli düğümün sağ çocuğunu güncelleriz.
    //Sağ çocuğa, yine aynı "insert" fonksiyonunu çağırarak sağ alt ağaca eklemeyi sürdürürüz.
    //Ekleme işlemi tamamlandığında, fonksiyon geçerli düğümü döndürür.
    //Bu, özyinelemeli çağrıları geri sarmaya başlamadan önce yapılan son işlemdir.
    private Node insert(Node node, int key) {
        if (node == null) {
            node = new Node(key);
            return node;
        }

        if (key < node.key) {
            node.left = insert(node.left, key);
        } else if (key > node.key) {
            node.right = insert(node.right, key);
        }

        return node;
    }

    //Bu fonksiyon, verilen anahtar değeri (key) ile ağaçta düğüm aramak için kullanılır.
    //Ağacın kök düğümünden (root) başlayarak, özyinelemeli yardımcı fonksiyon olan "search(Node node, int key)" fonksiyonunu çağırır.
    public Node search(int key) {
        return search(root, key);
    }
    //Bu fonksiyon, verilen anahtar değeri ile düğüm aramak için kullanılır.
    //Eğer geçerli düğüm (node) null ise, aranan anahtar değerinin ağaçta olmadığı sonucuna varır ve null döndürür.
    //Eğer aranan anahtar değeri (key) geçerli düğümün anahtar değeriyle eşleşiyorsa, aradığımız düğümü bulduk demektir ve bu düğümü döndürür.
    //Eğer aranan anahtar değeri (key) geçerli düğümün anahtar değerinden küçükse, sol alt ağaçta arama yapmaya devam etmek için aynı "search" fonksiyonunu çağırırız.
    //Eğer aranan anahtar değeri (key) geçerli düğümün anahtar değerinden büyükse, sağ alt ağaçta arama yapmaya devam etmek için aynı "search" fonksiyonunu çağırırız.
    private Node search(Node node, int key) {
        if (node == null) {
            return null;
        }

        if (key == node.key) {
            return node;
        } else if (key < node.key) {
            return search(node.left, key);
        } else {
            return search(node.right, key);
        }
    }

    //Bu fonksiyon, ağaçtaki düğümleri "inorder" (LNR: Left-Node-Right) sırasına göre dolaşmak için kullanılır.
    //Ağacın kök düğümünden (root) başlayarak,"inorderTraversal(Node node)" fonksiyonunu çağırır.
    public void inorderTraversal() {
        inorderTraversal(root);
    }
    //????????????????????????????
    //Bu fonksiyon, ağaçtaki düğümleri inorder sırasına göre dolaşmak için kullanılır.
    //Eğer geçerli düğüm (node) null değilse, aşağıdaki adımları uygular:
    //a. Sol alt ağaçta inorder dolaşmayı sürdürmek için aynı "inorderTraversal" fonksiyonunu çağırır.
    //b. Geçerli düğümün anahtar değerini yazdırır (şu anki satır yorum satırı olarak işaretli, etkinleştirmek için yorum işaretini kaldırabilirsiniz).
    //c. Sağ alt ağaçta inorder dolaşmayı sürdürmek için aynı "inorderTraversal" fonksiyonunu çağırır.
    private void inorderTraversal(Node node) {
        if (node != null) {
            inorderTraversal(node.left);
            // System.out.print(node.key + " ");
            inorderTraversal(node.right);
        }
    }

    public void isAVL() {
        avlTraversal(root);
        System.out.print("Is AVL: ");
        if(stats.isAVL()){
            System.out.println("yes");
        } else {
            System.out.println("no");
        }
    }

    public void printStats(){
        double average = stats.calculateAverage();
        System.out.println("min: " + stats.getMinElement() + ", max: " + stats.getMaxElement() + ", avg: " + average);
    }

    //Bu fonksiyon, ağaçtaki düğümleri dolaşarak AVL ağacı olup olmadığını kontrol etmek için kullanılır.
    //Eğer geçerli düğüm (node) null ise, fonksiyon sonlanır ve geri döner.
    //Aksi takdirde, geçerli düğümün anahtar değeri (node.key) ile aşağıdaki işlemler yapılır:
    //a. stats nesnesinde minimum elemanı günceller.
    //b. stats nesnesinde maksimum elemanı günceller.
    //c. stats nesnesinde toplam elemanları ve toplam değeri günceller.
    //Sağ alt ağaçta AVL dolaşmayı sürdürmek için aynı "avlTraversal" fonksiyonunu çağırır.
    //Sol alt ağaçta AVL dolaşmayı sürdürmek için aynı "avlTraversal" fonksiyonunu çağırır.
    //Geçerli düğümün dengelenme faktörünü (balanceFactor) hesaplar.
    //Dengelenme faktörünü ve düğümün anahtar değerini ekrana yazdırır.
    //Eğer dengelenme faktörü -1'den küçük veya 1'den büyükse, AVL ihlali olduğunu belirtir ve ekrana yazdırır.
    //Eğer ağaç hala AVL ağacı olarak kabul ediliyorsa ve ihlal meydana geldiyse, stats nesnesinde AVL durumunu false olarak günceller.

    private void avlTraversal(Node node) {

        if(node == null)
            return;


        stats.setMinElement(node.key);
        stats.setMaxElement(node.key);
        stats.addElement(node.key);


        avlTraversal(node.right);

        avlTraversal(node.left);

        int balanceFactor = node.getBalanceFactor();
        System.out.print("bal(" + node.key + ") = " + balanceFactor);
        if(balanceFactor < -1 || balanceFactor > 1)
        {
            System.out.print(" (AVL violation!)");
            if(stats.isAVL()){
                stats.setAVL(false);
            }
        }
        System.out.println();

    }
    //Bu fonksiyon, belirtilen anahtar (key) değerini ağaçta arar ve arama yolunu ekrana yazdırır.
    //İlk olarak, arama yolunu saklamak için kullanılan "findKeyPath" değişkenini boş bir dize olarak ayarlar.
    //Daha sonra, ağacın kök düğümünden (root) başlayarak "searchWithPath(Node node, int key)" fonksiyonunu çağırır ve arama sonucunu "found" değişkeninde saklar.
    //Eğer anahtar değeri bulunursa, ekrana anahtarın bulunduğunu ve arama yolunu yazdırır.
    //Eğer anahtar değeri bulunamazsa, ekrana anahtarın bulunamadığını yazdırır.
    //Arama sonucunu (true veya false) döndürür.
    public boolean searchWithPath(int key) {
        findKeyPath = "";
        boolean found = searchWithPath(root, key);
        if(found){
            System.out.println(key + " found " + findKeyPath);
        } else {
            System.out.println(key + " not found!");
        }
        return found;
    }
    //Bu fonksiyon, belirtilen anahtar değerini ağaçta arar ve arama yolunu "findKeyPath" değişkeninde saklar.
    //Eğer geçerli düğüm (node) null ise, arama başarısızdır ve false döndürür.
    //Eğer anahtar değeri, geçerli düğümün anahtar değerine eşitse, arama başarılıdır ve arama yoluna geçerli düğümün anahtar değerini ekler ve true döndürür.
    //Eğer anahtar değeri, geçerli düğümün anahtar değerinden küçükse, arama yoluna geçerli düğümün anahtar değerini ve bir virgül ekler, ardından sol alt ağaçta aramayı sürdürmek için aynı "searchWithPath" fonksiyonunu çağırır.
    //Eğer anahtar değeri, geçerli düğümün anahtar değerinden büyükse, arama yoluna geçerli düğümün anahtar değerini ve bir virgül ekler, ardından sağ alt ağaçta aramayı sürdürmek için aynı "searchWithPath" fonksiyonunu çağırır.
    private boolean searchWithPath(Node node, int key) {
        if (node == null) {
            return false;
        }

        if (key == node.key) {
            findKeyPath += node.key;
            return true;
        } else if (key < node.key) {
            findKeyPath += node.key + ", ";
            return searchWithPath(node.left, key);
        } else {
            findKeyPath += node.key + ", ";
            return searchWithPath(node.right, key);
        }
    }

    //İlk olarak, alt ağacın kök düğümünü (ana ağaçtaki) arar ve "startNode" değişkeninde saklar. Eğer kök düğüm bulunamazsa, "Subtree not found!" mesajını ekrana yazdırır ve fonksiyonu sonlandırır.
    //Sonrasında, alt ağaçtaki elemanların bulunup bulunmadığını takip etmek için kullanılacak bir "checked" adlı boolean dizi oluşturulur. İlk eleman (alt ağacın kök düğümü) bulunduğu için true olarak ayarlanır.
    //Özyinelemeli yardımcı fonksiyon "searchSubtree" çağrılır. Bu fonksiyona başlangıç düğümü (startNode), aranacak anahtar değeri (subtreeElements'in son elemanı), alt ağaç elemanları listesi ve checked dizisi parametre olarak verilir.
    //Yardımcı fonksiyondan döndükten sonra, checked dizisindeki tüm elemanların true olup olmadığı kontrol edilir. Eğer tüm elemanlar true ise, "Subtree found" mesajını ekrana yazdırır. Eğer en az bir eleman false ise, "Subtree not found!" mesajını ekrana yazdırır.
    public void searchSubtree(ArrayList<Integer> subtreeElements){

        Node startNode = search(subtreeElements.get(0));
        if(startNode == null){
            System.out.println("Subtree not found!");
            return;
        }

        boolean[] checked = new boolean[subtreeElements.size()];
        checked[0] = true;

        searchSubtree(
                startNode,
                subtreeElements.get(subtreeElements.size() - 1),
                subtreeElements,
                checked
        );

        for (boolean hasElementFound : checked) {
            if(!hasElementFound){
                System.out.println("Subtree not found!");
                return;
            }
        }
        System.out.println("Subtree found");

    }

    //Eğer geçerli düğüm (node) null ise, fonksiyonu sonlandırır.
    //Eğer geçerli düğümün anahtar değeri alt ağaç elemanları listesinde bulunuyorsa, checkedList'teki ilgili indeksi true olarak işaretler.
    //Eğer aranan anahtar değeri geçerli düğümün anahtar değerine eşitse, fonksiyonu sonlandırır.
    //Eğer aranan anahtar değeri geçerli düğümün anahtar değerinden küçükse, sol alt ağaçta aramayı sürdürmek için aynı "searchSubtree" fonksiyonunu çağırır.
    //Eğer aranan anahtar değeri geçerli düğümün anahtar değerinden büyükse, sağ alt ağaçta aramayı sürdürmek için aynı "searchSubtree" fonksiyonunu çağırır.
    private void searchSubtree(Node node, int key, ArrayList<Integer> subtreeElements, boolean[] checkedList) {

        if (node == null) {
            return;
        }

        if(subtreeElements.contains(node.key)){
            int nodeIndex = subtreeElements.indexOf(node.key);
            checkedList[nodeIndex] = true;
        }

        if (key == node.key) {
            return;
        } else if (key < node.key) {
            searchSubtree(node.left, key, subtreeElements, checkedList);
        } else {
            searchSubtree(node.right, key, subtreeElements, checkedList);
        }
    }
}