## Entities and Association Mapping
* Lazy Loading
  * The default in Hibernate 3 is that **classes** (including **collections** like Set and Map) should be lazily loaded.
* Associations
  * `<one-to-one>`
  ```xml
  <id name="id" column="product">
    <generator class="foreign">
      <param name="property">campaign</param>
    </generator>
  </id>
  <one-to-one name="campaign" class="Compaign" constrained="true"/>
  ```
  * `<many-to-one>`
  ```xml
  <many-to-one name="email" class="Email" column="email" cascade="all" unique="true"/>
  <!-- An alternative is to use a link table to combine the two entities.  -->
  <join table="link_email_user" inverse="true" optional="false">
    <key column="user_id"/>
    <many-to-one name="email" column="email_id" not-null="true"/>
  </join>
  ```
    * The disadvantage of the link table approach is its slightly poorer performance (it requires a join of three tables to retrieve the associations, rather than one).
    * Its benefit is that it requires less extreme changes to the schema if the relationship is modified—typically, changes would be made to the link table, rather than to one of the entity tables.
* Collections and Associations
```XML
<!-- An implementation of mapping a set of strings into a property called titles -->
<set name="titles" table="nameset">
  <key column="titleid"/>
  <element type="string" column="name" not-null="true"/>
</set>
<!--
Here we map Phone entities from the “many” side of a one-to-many association into a Set property,
called phoneNumbers, that belongs to a User entity
-->
<set name="phoneNumbers" inverse="true">
  <key column="aduser"/>
  <one-to-many class="sample.Phone"/>
</set>
<!-- Unidirectional and bidirectional lists -->
<class name="Item" table="ITEM">
  ...
  <list name="bids">
    <key column="ITEM_ID" not-null="true"/>
    <list-index column="BID-POSITION"/>
    <one-to-many class="Bid"/>
  </list>
</class>
<!-- There is no inverse="true" for a many-to-one mapping -->
<class name="Bid" table="BID">
  ...
  <many-to-one name="item" column="ITEM_ID" class="Item" not-null="true" insert="false" update="false"/>
</class>
<!-- many-to-many -->
<set name="boughtItems" table="ITEM_BUYER">
  <key column="USER_ID"/>
  <many-to-many class="Item" column="ITEM_ID" unique="true"/>
</set>
<join table="ITEM_BUYER" optional="true" inverse="true">
  <key column="ITEM_ID" unique="true" not-null="true"/>
  <many-to-one name="buyer" column="USER_ID"/>
</join>
<!-- Unidirectional many-to-many Associations -->
<set name="items" table="CATEGORY_ITEM" cascade="save-update">
  <key column="CATEGORY_ID"/>
  <many-to-many class="Item" column="ITEM_ID"/>
</set>
<!-- In Hibernate XML you can also switch to an idbag with a separate PK column on the join table -->
<idbag name="items" table="CATEGORY_ITEM" cascade="save-update">
  <collection-id type="long" column="CATEGORY_ITEM_ID">
    <generator class="sequence"/>
  </collection-id>
  <key column="CATEGORY_ID"/>
  <many-to-many class="Item" column="ITEM_ID"/>
</idbag>
<!-- bidirectional many-to-many Associations -->
<class name="Category" table="CATEGORY">
  ...
  <set name="items" table="CATEGORY_ITEM" cascade="save-update">
    <key column="CATEGORY_ID"/>
    <many-to-many class="Item" column="ITEM_ID"/>
  </set>
</class>
<class name="Item" table="ITEM">
  ...
  <set name="categories" table="CATEGORY_ITEM" inverse="true" cascade="save-update">
    <key column="ITEM_ID"/>
    <many-to-many class="Category" column="CATEGORY_ID"/>
  </set>
</class>
```

## Searches and Queries



## Annotations
* Uses
  * Information for the compiler
  * Compiler-time and deployment-time processing
  * Runtime processing
* Annotations Used by the Compiler
  * `@Deprecated`
  ```java
  // Javadoc comment follows
  /*
  * @deprecated
  * explanation of why it was deprecated
  */
  @Deprecated
  static void deprecatedMethod() {}
  ```
  * `@Override`
  ```java
  // mark method as a superclass method
  // that has been overridden
  @Override
  int overriddenMethod() {}
  ```
  * `@SuppressWarnings`
  ```java
  // use a deprecated method and tell
  // compiler not to generate a warning
  @SuppressWarnings("deprecation")
  void useDeprecatedMethod() {
    objectOne.deprecatedMethod(); //deprecation warning - suppressed
  }
  // To suppress more than one category of warnings, use the following syntax:
  @SuppressWarnings({"unchecked", "deprecation"})
  ```
* Annotation Processing
```java
import java.lang.annonation.*;
@Rentention(RententionPolicy.RUNTIME)
@interface AnnonationForRuntime {
  // Elements that give Information
  // for runtime processing
}
```

## Searches and Queries
* Syntax Basics
  * **UPDATE**
  ```sql
  update [versioned]            -- versioned means that the update will update time stamps, if any, that are part of the entity being updated
    [from] path [[as] alais] [, ...]  
    set property = value [, ...]  
    [where logicalExpression]
  ```
  ```Java
  Query query = session.createQuery("update Person set creditscore=:creditscore where name=:name");
  query.setInteger("creditscore", 612);
  query.setString("name", "John Q. Public");
  int modifications = query.executeUpdate();
  ```
  * **DELETE**
  ```sql
  delete
    [from] path [[as] alias]
    [where logicalExpression]
  ```
  ```Java
  Query query = session.createQuery("delete from Person where accountstatus=:status");
  query.setString("status", "purged");
  int rowsDeleted = query.executeUpdate();
  ```
  * **INSERT**
  ```sql
  insert into path (property [, ...])
  select
  ```
  ```java
  Query query = session.createQuery("insert into purged_users(id, name, statud) " +
                                    "select id, name, status from users where status=:status");
  query.setString("status", purged);
  int rowsCopied = query.executeUpdate();
  ```
  * **SELECT**
  ```sql
  [select [distinct] property [, ...]]
    from path [[as] alias], [, ...] [fetch all properties]  -- If fetch all properties used, then lazy loading semantics will be ignored
    where logicalExpression
    group by property [, ...]
    having logicalExpression
    order by property [asc | desc] [, ...]
  ```
* Named Queries
  * Named queries are created via class-level annotations on entities; normally, the queries apply to the entity in whose source file they occur, but there’s no absolute requirement for this to be true.
  * Named queries are created with the `@NamedQueries` annotation, which contains an array of `@NamedQuery` sets; each has a query and a name.
  * Example
  ```Java
  @Entity
  public class Supplier implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer id;
    @Column(unique = true)
    @NotNull
    String name;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "supplier", targetEntity = Product.class)
    List<Product> products = new ArrayList<>();
  }
  ```
  ```Java
  @Entity
  public class Product implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer id;
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    Supplier supplier;
    @Column
    @NotNull
    String name;
    @Column
    @NotNull
    String description;
    @Column
    @NotNull
    Double price;
  }
  ```
  ```Java
  @Entity
  public class Software extends Product implements Serializable {
    @Column
    @NotNull
    String version;
  }
  ```
  ```Java
  // Adding a named query is as simple as adding an annotation to one of the entities
  @NamedQuery(name = "supplier.findAll", query = "from Supplier s")
  @NamedQueries({
    @NamedQuery(name = "supplier.findAll", query = "from Supplier s"),
    @NamedQuery(name = "supplier.findByName", query = "from Supplier s where s.name=:name")
  })
  ```
* Getting Named Query
```Java
Query query = session.getNamedQuery("supplier.findAll");
List<Supplier> suppliers = query.list();
// Add a comment to the SQL that Hibernate creates, which is useful for tracing which HQL statements correspond to which SQL statements
Query query = session.createQuery("from Product");
query.setComment("This is only a query for product");
List<Product> products = query.list();
```
* Creating the Query Object
* Logging and Commenting the Underlying SQL
* The from Clause and Aliases
* Using Package Names
* Using Named Paramaters
```Java
String hql = "from Product where price > :price";
Query query = session.createQuery(hql);
query.,setDouble("price", 25.0);
List results = query.list();
```
* Paging Through the Result Set
```Java
Query query = session.createQuery("from Product");
query.setFirstResult(1);  // represents the first row in your result set, starting with row 0
query.setMaxResult(2);    // only retrieve a fixed number of objects
List results = query.list();
```
* Obtaining a Unique Result from an HQL Query
```Java
String hql = "from Product where price > 25.0";
Query query = session.createQuery(hql);
query.setMaxResults(1);
Product product = (Product)query.uniqueResult();
// If there is more than one result, then throws a NonUNiqueResultException
```
* Sorting Results with the order by Clause
```sql
from Product p where p.price > 25.0 order by p.price desc
from Product p order by p.supplier.name asc, p.price asc
```
* Associations
```sql
-- cross join
from Product p, Supplier s
-- inner join
from Product p inner join p.supplier as s
-- Use fetch for a query like this, Hibernate will return only the Supplier objects
from Supplier s inner join fetch s.products as p
```
* Aggregate Methods
* Bulk Updates and Deletes with HQL
* Use Native SQL
```Java
String sql = "select avg(product.price) as avgPrice from Product product";
SQLQuery query = session.createSQLQuery(sql);
query.addScalar("avgPrice", Hibernate.DOUBLE);
List results = query.list();
// entities
String sql = "select {supplier.*} from Supplier supplier";
SQLQuery query = session.createSQLQuery(sql);
query.addEntity("supplier", Supplier.class);
List results = query.list();
```

## Annatations in Hibernate
* Annotated Java class
```java
@Entity                                           // Declares this an entity bean
@Table(name = "people")                           // Maps the bean to SQL table "people"
class Person implements Serializable {
  @Id                                             // Map this to the primary key column.
  @GeneratedValue(strategy = GenerationType.AUTO) // Database will generate new primary keys
  private Integer id;

  @Column(length = 32)                            // Truncate column values to 32 characters
  private String name;
  // getter and setter
}
```
* Basic Annonations
```java
// Declares this an entity bean
@Entity
// Identity
@Id
@EmbeddedId
@GeneratedValue
// Database Schema Attributes
@Table
@Column
// Relationship mappings
@OneToOne
@ManyToOne
@OneToMany
```
* Extension Annotations
  * Contained in `org.hibernate.annonations` package
  * Examples:
    * `@org.hibernate.annotation.Entity`
    * `@org.hibernate.annotations.Table`
    * `@BatchSize`
    * `@Where`
    * `@Check`

## Advanced Queries Usingh Criteria
* Using the Criteria API
```Java
Criteria crit = session.createCriteria(Product.class);
List<Product> results = crit.list();
```
* Using Restrictions with Criteria
* Creating Criterion Object
  * `eq()` & `ne()` Methods
  ```Java
  Criteria crit = session.createCriteria(Product.class);
  crit.add(Restrictions.eq("description", "Tablet")); // equal method
  crit.add(Restrictions.ne("description", "Tablet")); // non-equal method
  List<Product> results = crit.list();
  ```
  * `like()` & `ilike()` Methods
  ```Java
  // ilike() is case-insensitive
  Criteria crit = session.createCriteria(Product.class);
  crit.add(Restrictions.like("name", "Tab%")); // use the % character as a wildcard to match parts of the string
  crit.add(Restrictions.like("description", "ser", MatchMode.END)); // ANYWHERE, END, EXACT, START
  List<Product> results = crit.list();
  ```
  * `isNull()` & `isNotNull()` restrictions
  ```Java
  Criteria crit = session.createCriteria(Product.class);
  crit.add(Restrictions.isNull("name"));
  List<Product> results crit.list();
  ```
  * `gt()`, `ge()`, `lt()`, `le()` restrictions
  ```Java
  Criteria crit = session.createCriteria(Product.class);
  crit.add(Restricytions.gt("price", 25.0));
  List<Product> results = crit.list();
  ```
  * AND and OR Restrictions
  ```Java
  // AND
  Criteria crit = session.createCriteria(Product.class);
  crit.add(Restrictions.lt("price", 10.0));
  crit.add(Restrictions.ilike("description", "mouse", MatchMode.ANYWHERE));
  List<Product> results = crit.list();
  // OR
  Criteria crit = session.createCriteria(Product.class);
  Criterion priceLessThan = Restrictions.lt("price", 10.0);
  Criterion tablet = Restrictions.ilike("description", "tablet", MatchMode.ANYWHERE);
  LogicalExpression orExp = Restrictions.or(priceLessThan, tablet);
  crit.add(orExp);
  List<Product> results = crit.list();
  // OR expression with more than 2 different criteria
  Criteria crit = session.createCriteria(Product.class);
  Criterion priceLessThan = Restrictions.lt("price", 10.0);
  Criterion mouse = Restrictions.ilike("description", "mouse", MatchMode.ANYWHERE);
  Criterion browser = Restrictions.ilike("description", "browser", MatchMode.ANYWHERE);
  Disjunction disjunction = Restrictions.disjunction();
  disjunction.add(priceLessThan);
  disjunction.add(mouse);
  disjunction.add(browser);
  crit.add(disjunction);
  List<Product> results = crit.list();
  ```
  * `sqlRestriction()`
  ```Java
  Criteria crit = session.createCriteria(Product.class);
  crit.add(Restrictions.sqlRestriction("{alias}.description like 'Tab%'")); // use {alias} to signify the class's table
  List<Product> results = crit.list();
  ```
  * `setFirstResult()` and `setMaxResults()`
  ```Java
  Criteria crit = session.createCriteria(Product.class);
  crit.setFirstResult(1);
  crit.setMaxResults(20);
  List<Product> results = crit.list();
  ```
  * Obtaining a Unique Result
  ```Java
  Criteria crit = session.createCriteria(Product.class);
  Criterion price = Restrictions.gt("price", new Double(25.0));
  crit.setMaxResults(1);
  Product product = (Product)crit.uniqueResult();
  ```
  * Sorting the Query's Results
  ```Java
  Criteria crit = session.createCriteria(Product.class);
  crit.add(Restrictions.gt("price", 10.0));
  crit.addOrder(Order.desc("price")); // Order.asc("price")
    List<Product> results = crit.list();
  ```
  * Associations
  ```Java
  // one-to-many associations
  Criteria crit = session.createCriteria(Supplier.class);
  Criteria prdCrit = crit.createCriteria("products");
  prdCrit.add(Restrictions.gt("price", 25.0));
  List<Supplier> results = crit.list();
  // many-to-one associations
  Criteria crit = session.createCriteria(Product.class);
  Criteria suppCrit = crit.createCriteria("supplier");
  suppCrit.add(Restrictions.eq("name", "Hardware Are We"));
  List<Product> results = crit.list();
  ```
  * Distinct Results
  * Projections and Aggregates
  ```Java
  // Single Projection
  Criteria crit = session.createCriteria(Product.class);
  crit.setProjection(Projections.rowCount());
  List<Long> results = crit.list();
  // More than one projection
  Criteria crit = session.createCriteria(Product.class);
  ProjectionList projList = Projections.projectionList();
  projList.add(Projections.max("price"));
  projList.add(Projections.min("price"));
  projList.add(Projections.avg("price"));
  projList.add(Projections.countDistinct("description"));
  crit.setProjection(projList);
  List<Object[]> results = crit.list();
  // Multiple Aggregate Projections
  Criteria crit = session.createCriteria(Product.class);
  ProjectionList projList = Projections.projectionList();
  projList.add(Projections.property("name"));
  projList.add(Projections.property("description"));
  crit.setProjection(projList);
  List<Object[]> results = crit.list();
  // Group By
  ProjectionList projList = Projections.projectionList();
  projList.add(Projections.groupProperty("name"));
  projList.add(Projections.groupProperty("price"));
  crit.setProjection(projList);
  crit.addOrder(Order.asc("price"));
  List<Object> results = crit.list();
  ```
* Query By Example
  ```Java
  Criteria crit = session.createCriteria(Supplier.class);
  Supplier supplier = new Supplier();
  supplier.setName("Megalnc");
  crit.add(Example.create(supplier));
  List<Supplier> results = crit.list();
  ```
  * Excluding Zero-Valued Properties
  ```Java
  Criteria crit = session.createCriteria(Product.class);
  PRoduct exampleProduct = new Product();
  exampleProduct.setName("Mouse");
  Example example = Example.create(exampleProduct);
  example.excludeZeros();
  crit.add(example);
  List<Product> results = crit.list();
  ```
  * Associations in QBE
  ```Java
  Criteria prdCrit = session.createCriteria(Product.class);
  Product product = new Product();
  product.setName("M%");
  Example prdExample = Example.create(product);
  prdExample.excludeProperty("price");
  prdExample.enableLike();
  Criteria suppCrit = prdCrit.createCriteria("supplier");
  Supplier supplier = new Supplier();
  supplier.setName("SuperCorp");
  suppCrit.add(Example.create(supplier));
  prdCrit.add(prdExample);
  List<Product> results = prdCrit.list();
  ```

## Mapping class inheritance
* Table per concrete class with implicit polymorphism
  * This approach is recommended (only) for the top level of your class hierarchy, where polymorphism isn’t usually required, and when modification of the superclass in the future is unlikely
* Table per concrete class with unions
  * Using `<union-subclass>`
  ```xml
  <hibernate-mapping>
    <class name="BillingDetails" abstract="true">
      <id name="id" column="BILLING_DETAILS_ID" type="long">
        <generator class="native"/>
      </id>
      <property name="name" column="OWNER" type="string"/>
      ...
      <union-subclass name="CreditCard" table="CREDIT_CARD">
        <property name="number" column="NUMBER"/>
        <property name="expMonth" column="EXP_MONTH"/>
        <property name="expYear" column="EXP_YEAR"/>
      </union-subclass>
      <union-subclass name="BankAccount" table="BANK_ACCOUNT">
        ...
      </union-subclass>
    </class>
  </hibernate-mapping>
  ```
* Table per class hierarchy
  * Columns for properties declared by subclasses must be declared to be nullable.
  * Using `<discriminator>` and `<subclass>`
  ```XML
  <hibernate-mapping>
    <class name="BillingDetails" table="BILLING_DETAILS">
      <id name="id" column="BILLING_DETAILS_ID" type="long">
        <generator class="native"/>
      </id>
      <discriminator column="BILLING_DETAILS_TYPE" type="string"/>
      <property name="owner" column="OWNER" type="string"/>
      ...
      <subclass name="CreditCard" discriminator-value="CC">
        <property name="number" column="CC_NUMBER"/>
        <property name="expMonth" column="CC_EXP_MONTH"/>
        <property name="expYear" column="CC_EXP_YEAR"/>
      </subclass>
      <subclass name="BankAccount" discriminator-value="BA"/>
        ...
      </subclass>
    </class>  
  </hibernate-mapping>
  ```
  * The disadvantages of the table per class hierarchy strategy may be too serious for your design—after all, denormalized schemas can become a major burden in the long run.
* Table per subclass
  * Represent inheritance relationships as relational foreign key associations.
  * Using `<joined-subclass>`
  ```xml
  <hibernate-mapping>
    <class name="BillingDetails" table="BILLING_DETAILS">
      <id name="id" column="BILLING_DETAILS_ID" type="long">
        <generator class="native"/>
      </id>
      <property name="owner" column="OWNER" type="string"/>
      ...
      <joined-subclass name="CreditCard" table="CREDIT_CARD">
        <key column="CREDIT_CARD_ID"/>
        <property name="number" column="NUMBER"/>
        <property name="expMonth" column="EXP_MONTH"/>
        <property name="expYear" column="EXP_YEAR"/>
      </joined-subclass>
      <joined-subclass name="BankAccount" table="BANK_ACCOUNT">
        ...
      </joined-subclass>
    </class>
  ```
* Mixing inheritance strategies
  * Using `<join>`
  ```xml
  <hibernate-mapping>
    <class name="BillingDetails" table="BILLING_DETAILS">
        <id>...</id>
        <discriminator column="BILLING_DETAILS_TYPE" type="string"/>
        ...
        <subclass name="CreditCard" discriminator-value="CC">
          <join table="CREDIT_CARD">
            <key column="CREDIT_CARD_ID"/>
            <property name="number" column="CC_NUMBER"/>
            <property name="expMonth" column="CC_EXP_MONTH"/>
            <property name="expYear" column="CC_EXP_YEAR"/>
          </join>
        </subclass>
        <subclass name="BankAccount" discriminator-value="BA">
          <property name="account" column="BA_ACCOUNT"/>
          ...
        </subclass>
    </class>
  </hibernate-mapping>
  ```
