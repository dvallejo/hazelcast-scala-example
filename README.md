hazelcast-scala-example
=======================
[![Build Status](https://travis-ci.org/dvallejo/hazelcast-scala-example.svg?branch=master)](https://travis-ci.org/dvallejo/hazelcast-scala-example)

# What is Hazelcast?

Hazelcast is an in-memory data grid technology. By using Hazelcast, in-memory distributed data structures can be easily implemented. This kind of structures are very useful for sharing key data among several hosts, among other uses.

The example in this template implements a server and a client that share an IMap (distributed map). This map will represent a shopping list. One instance, aka the mother, can add and remove items from the list. The other instance, aka the child, can only read the elements in the list.

Let's see how this is implemented.

# Hazelcast Server

In order to create an IMap, a Hazelcast instance must be created first:
```
val hazelcastInstance = Hazelcast.newHazelcastInstance(new Config())
```

Afterwards, an IMap named "shoppingList" is created. This map contains the name of the item of the shopping-list and the number of items of that type:
```
val shoppingList = hazelcastInstance.getMap[String, Int]("shoppingList")
```

The logic for adding new items to the map is implemented in the following piece of code:
```
shoppingList.put(item, amount)
```

For removing items from the list, the following code is used:
```
shoppingList.remove(item)
```
# Hazelcast Client

In order to obtain an IMap, a Hazelcast client instance must be created first:
```
val hazelcastClient = HazelcastClient.newHazelcastClient(new ClientConfig())
```

For obtaining the map, the next action is performed:
```
val shoppingList = hazelcastClient.getMap[String, Int]("shoppingList")
```      

Finally, the items of the map can be extracted by means of their keys and corresponding values.

# How can I test it?</h2>

An API has been created for testing the functionality of the example: ApiActor.

For simulating the behaviour of the child and reading the shopping list, you can access to the URL :
```
0.0.0.0:5000/child/list
```

For simulating the addition of an element (done by the mother instance), a POST request must be performed. For example, if you want to add three oranges, you should send a POST request to:

```
0.0.0.0:5000/mom/list/orange?n=3
```

For simulating the removal of all the items of a product (all oranges, all apples...), a DELETE request must be performed to :
```
0.0.0.0:5000/mom/list/product_name
```
where product_name is substituted by the name of the product (apple, orange...)
