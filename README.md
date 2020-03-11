cs3211-project
===========
A simple parallel web crawler.

Setup
-----------
Make sure you have the correct path for jdk.

Project structure
-------------------

**What we are going to make**

We are going to make a web crawler that makes use of multiple threads in order to keep the machine busy all the time. The application is IO-bound, because fetching a website takes a long time, and it blocks the thread of execution, which is not desirable. Instead, we are going to create a lot of threads doing blocking IO in order to keep sending HTTP requests to the URLs we have not visited yet.
**Dependencies**

JDK (not too old is ok)JSoup for doing blocking fetches and parsing HTML (can be changed later)(We can use a transactional database which automatically enforces ACID principle so we don’t have to worry about concurrency issues, but well)
**Architecture**

We will have a single thread for the event loop. There is only one event: Timer interrupt to display no. of URL fetched each hour.
We will also have a lot of threads to do the basic task of read URL -> fetch URL -> parsing -> write URL.The read and write should be atomic, but the execution in the middle can be done concurrently without any issue.
We will use a data structure that supports atomic read and write to avoid data races.

**That very data structure would be…**(From course project pptx page 5)
<Buffer List> ← write ← <Worker/Fetcher threads> → read → <Database>
<Buffer List> ← read ← <Indexing threads>       → write → <Database>

![img](https://lh6.googleusercontent.com/mgKuzoX2Eeg9Zj6Lt9Uh5cLh4e9FTyvrMWqcaSWpNCm_Zvj_BKqd92L_08l1NXRBz6unyIWLvc8ZjCYGuPe4M9h4WvfXhTohJD1d5MAJUhw5uu_tUxOLQiTDUzFdbORtYQEROr9t)
**Potential data races**

a) When two CT read the IUT at the same time, both of them might mark the same URL as PROCESSING at the same time, so they might be going to fetch the same URL.See the reader-writer problem, in this case we don’t want to have multiple reads at the same time to prevent double fetching, so reading should also be atomic.
**Potential deadlocks**

If we allow both IBT and CT to access the buffer at the same time, then it could happen that the buffer invariant is not maintained. For example, the size of the buffer might be incorrect.
**How to solve the above problems?**

We will try to declare critical regions using semaphores and mutexes. We will try to use CSP to verify our model.