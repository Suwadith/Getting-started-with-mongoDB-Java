import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

/**
 * Created by Suwadith on 7/13/2017.
 */
public class ConnectionDemo {

    public static void main(String[] args){

        //Connects to your local mongoDB server
        MongoClient mongoClient = new MongoClient("localhost", 27017);

        //Alternative code to make new connection
        /*MongoClientURI connectionString = new MongoClientURI("mongodb://localhost:27017");
        MongoClient mongoClient = new MongoClient(connectionString);*/

        //Retrieves the Database named: "TestDB"
        //If it's not there It';ll create one
        MongoDatabase database = mongoClient.getDatabase("TestDB");

        //Creates a new Collection inside the DB that you just created or retrieved
        MongoCollection collection = database.getCollection("TestData");

        //Creates a new Document named "doc1"
        //Since mongoDB is schema less you don't have to worry about extra fields or missing fields.
        //Everything will be considered as key, value pairs
        Document doc1 = new Document("Name: ", "Suwadith")
                .append("Age", "21")
                .append("Gender", "Male")
                .append("Address", new Document("No", "221B").append("Street", "Baker").append("City", "London"));

        //Inserts the newly created document on to the Collection that we created
        collection.insertOne(doc1);

        //Stores the first document that's inside the collection to the variable named myDoc
        Document myDoc = (Document) collection.find().first();

        //Prints the document in JSON format
        System.out.println(myDoc.toJson());

    }

}
