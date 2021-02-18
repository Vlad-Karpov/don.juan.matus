/*******************************************************************************
                    left rotation
                    
             dad (db)                        dad (dbn)
           /    \                          /       \
          /      \                        /         \
        /\        current (cb)           /\          pivot (pbn)
       /dl\      /\     \               /dl\       /         /\
      /____\    /cl\     \             /____\     /         /pr\
               /____\    pivot (pb)          current (cbn) /____\
                        /\     /\            /\      /\
                       /pl\   /pr\          /cl\    /pl\
                      /____\ /____\        /____\  /____\
                      
          
db(n) dad balance factor (new)
cb(n) current balance factor (new)          
pb(n) pivot balance factor (new)

dl dad left subtree
cl current left subtree
pl pivot left subtree
pr pivot right subtree

H(...) hight subtree
       
*******************************************************************************/

/*
n, a, b, c, d - integer

0) max(a, b) = max(b, a)
1) n + max(a, b) = max(n + a, n + b)
2) n * max(a, b) = max(n * a, n * b)   n >= 0
3) n * max(a, b) = min(n * a, n * b)   n < 0   =>  max(a, b) = - min(-a, -b)
4) max(max(a, b), max(c, d)) = max(a, b, c, d)
5) max(a, b) + max(c, d) = max(a + c, a + d, b + c, b + d)
6) max(0, 1, 2, a) = max(2, a)

0) min(a, b) = min(b, a)
1) n + min(a, b) = min(n + a, n + b)
2) n * min(a, b) = min(n * a, n * b)   n >= 0
3) n * min(a, b) = max(n * a, n * b)   n < 0   =>  min(a, b) = - max(-a, -b)
4) min(min(a, b), min(c, d)) = min(a, b, c, d)
5) min(a, b) + min(c, d) = min(a + c, a + d, b + c, b + d)
6) min(0, 1, 2, a) = max(0, a)
*/
  
 pb = H(pl) - H(pr)
    ->  H(pr) = H(pl) - pb
    ->  H(pl) = H(pr) + pb
 cb = H(cl) - (1 + max(H(pl), H(pr)))
    ->  H(cl) = max(H(pl), H(pr)) + 1 + cb 

cbn?  H(cl) - H(pl)
pbn?  (max(H(cl), H(pl)) + 1) - H(pr)

--------------------------------- cbn ------------------------------------------

cbn = max(pb, 0) + 1 + cb - pb
 

SELECT pb.b pbb, cb.b cbb, (maxx(pb.b, 0) + 1 + cb.b - pb.b) cbn
  FROM (SELECT -2 b FROM DUAL
        UNION
        SELECT -1 b FROM DUAL) cb,
       (SELECT -1 b FROM DUAL
        UNION
        SELECT 0 b FROM DUAL
        UNION 
        SELECT 1 b FROM DUAL) pb
        
/*        
pb    cb    cbn
-1    -2     0
 0    -2    -1
 1    -2    -1
-1    -1     1
 0    -1     0
 1    -1     0
*/
        
--------------------------------- pbn ------------------------------------------

 pb = H(pl) - H(pr)     ->  H(pr) = H(pl) - pb   ->  H(pl) = H(pr) + pb
 cb = H(cl) - (1 + max(H(pl), H(pr))) - > H(cl) = max(H(pl), H(pr)) + 1 + cb  

pbn?  (max(H(pl), H(cl)) + 1) - H(pr)

max(H(pl), H(cl)) + 1 - H(pr)
max(H(pl) + 1 - H(pr), H(cl)+ 1 - H(pr))
max(pb, H(cl) - H(pr)) + 1
max(pb, max(pb, 0) + 1 + cb) + 1

pbn = max(pb, max(pb, 0) + 1 + cb) + 1


SELECT pb.b pbb, cb.b cbb, (maxx(pb.b, maxx(pb.b, 0) + 1 + cb.b) + 1) pbn
  FROM (SELECT -2 b FROM DUAL
        UNION
        SELECT -1 b FROM DUAL) cb,
       (SELECT -1 b FROM DUAL
        UNION
        SELECT 0 b FROM DUAL
        UNION
        SELECT 1 b FROM DUAL) pb

/*
pb    cb    pbn    
-1    -2    0
 0    -2    1
 1    -2    2
-1    -1    1
 0    -1    1
 1    -1    2        
*/

/*******************************************************************************
                    right rotation
       
          current (cb)           pivot (pbn)    
         /          /\           /\       \
        /          /cr\         /pl\       \
    pivot (pb)    /____\       /____\     current (cbn)    
   /\     /\                              /\        /\          
  /pl\   /pr\                            /pr\      /cr\
 /____\ /____\                          /____\    /____\
          
          
cb(n) current balance factor (new)          
pb(n) pivot balance factor (new)

cl current left subtree
pl pivot left subtree
pr pivot right subtree

H(...) hight subtree
       
*******************************************************************************/

 pb = H(pl) - H(pr)
    ->   H(pr) = H(pl) - pb
    ->   H(pl) = H(pr) + pb
 cb = max(H(pl), H(pr)) + 1 - H(cr)
    ->   H(cr) = max(H(pl), H(pr)) + 1 - cb

cbn?  H(pr) - H(cr)
pbn?  H(pl) - (max(H(pr), H(cr)) + 1) 

--------------------------------- cbn ------------------------------------------

cbn = min(0, pb) - 1 + cb - pb 

SELECT pb.b pbb, cb.b cbb, (minn(0, pb.b) - 1 + cb.b - pb.b) cbn
  FROM (SELECT 2 b FROM DUAL
        UNION
        SELECT 1 b FROM DUAL) cb,
       (SELECT -1 b FROM DUAL
        UNION
        SELECT 0 b FROM DUAL
        UNION 
        SELECT 1 b FROM DUAL) pb
        
/*
pb    cb    cbn
-1    2     1
 0    2     1
 1    2     0
-1    1     0
 0    1     0
 1    1    -1
*/        

--------------------------------- pbn ------------------------------------------

 pb = H(pl) - H(pr)
    ->   H(pr) = H(pl) - pb
    ->   H(pl) = H(pr) + pb
 cb = max(H(pl), H(pr)) + 1 - H(cr)
    ->   H(cr) = max(H(pl), H(pr)) + 1 - cb

pbn?  H(pl) - (max(H(pr), H(cr)) + 1) 

pbn = min(pb, min(0, pb) - 1 + cb) - 1

SELECT pb.b pbb, cb.b cbb, (minn(pb.b, minn(0, pb.b) - 1 + cb.b) - 1) pbn
  FROM (SELECT 2 b FROM DUAL
        UNION
        SELECT 1 b FROM DUAL) cb,
       (SELECT -1 b FROM DUAL
        UNION
        SELECT 0 b FROM DUAL
        UNION 
        SELECT 1 b FROM DUAL) pb

/*
PB, CB, PBN
-1,  1,  -2
 0,  1,  -1
 1,  1,  -1
-1,  2,  -2
 0,  2,  -1
 1,  2,   0
*/



CREATE OR REPLACE FUNCTION minn (a1 NUMBER, a2 NUMBER)
   RETURN NUMBER
AS
BEGIN
   IF a1 < a2
   THEN
      RETURN a1;
   ELSE
      RETURN a2;
   END IF;
END;

CREATE OR REPLACE FUNCTION maxx (a1 NUMBER, a2 NUMBER)
   RETURN NUMBER
AS
BEGIN
   IF a1 > a2
   THEN
      RETURN a1;
   ELSE
      RETURN a2;
   END IF;
END;






/*******************************************************************************
                    left rotation current is right
                    
             dad (db)                        dad (dbn)
           /    \                          /       \
          /      \                        /         \
        /\        current (cb)           /\          pivot (pbn)
       /dl\      /\     \               /dl\       /         /\
      /____\    /cl\     \             /____\     /         /pr\
               /____\    pivot (pb)          current (cbn) /____\
                        /\     /\            /\      /\
                       /pl\   /pr\          /cl\    /pl\
                      /____\ /____\        /____\  /____\
                      
          
db(n) dad balance factor (new)
cb(n) current balance factor (new)          
pb(n) pivot balance factor (new)

dl dad left subtree
cl current left subtree
pl pivot left subtree
pr pivot right subtree

H(...) hight subtree
       
*******************************************************************************/
db =  H(dl) - max(H(cl)+ 1, H(pl) + 2, H(pr) + 2)
dbn = H(dl) - max(H(cl)+ 2, H(pl) + 2, H(pr) + 1)

db =  H(dl) - max(H(cl) - 1, H(pl), H(pr)) + 2
dbn = H(dl) - max(H(cl), H(pl), H(pr) - 1) + 2

dbn - db = max(H(cl) - 1, H(pl), H(pr)) - max(H(cl), H(pl), H(pr) - 1)
dbn - db = max(H(cl) - 1, H(pl), H(pr)) - max(H(cl), H(pl), H(pr) - 1) + H(pl) - H(pl)
dbn - db = max(H(cl) - 1, H(pl), H(pr)) + min(-H(cl), -H(pl), -H(pr) + 1) + H(pl) - H(pl)
dbn - db = max(H(cl) - H(pl)- 1, H(pl)- H(pl), H(pr)- H(pl)) + min(+ H(pl)-H(cl), + H(pl)-H(pl), + H(pl)-H(pr) + 1)
dbn - db = max(cbn - 1, 0, -pb) + min(-cbn, 0, 1 + pb)
dbn = db + max(cbn - 1, 0, -pb) + min(-cbn, 0, 1 + pb)


/*******************************************************************************
                    right rotation current is left
       
                     dad (db)                       dad (dbn)
                    /        \                   /           \
                   /         /\                 /            /\
          current (cb)      /dr\             pivot (pbn)    /dr\
          /          /\    /____\            /\       \    /____\
         /          /cr\                    /pl\       \
     pivot (pb)    /____\                  /____\     current (cbn)    
     /\     /\                                        /\        /\          
    /pl\   /pr\                                      /pr\      /cr\
   /____\ /____\                                    /____\    /____\

cb(n) current balance factor (new)
pb(n) pivot balance factor (new)

cl current left subtree
pl pivot left subtree
pr pivot right subtree

H(...) hight subtree
       
*******************************************************************************/

db =  max(H(cr)+ 1, H(pr) + 2, H(pl) + 2) - H(dr)
dbn = max(H(cr)+ 2, H(pr) + 2, H(pl) + 1) - H(dr)

db =  max(H(cr) - 1, H(pr), H(pl)) - H(dr) + 2
dbn = max(H(cr), H(pr), H(pl) - 1) - H(dr) + 2

db - dbn = max(H(cr) - 1, H(pr), H(pl)) - max(H(cr), H(pr), H(pl) - 1)
db - dbn = max(H(cr) - 1, H(pr), H(pl)) + min(-H(cr), -H(pr), 1 - H(pl)) + H(pr) - H(pr)
db - dbn = max(- cbn - 1, 0, pb) + min(cbn, 0, 1 - pb)
1 rl) dbn = db - max(- cbn - 1, 0, pb) - min(cbn, 0, 1 - pb)

2 rr) dbn = db + max(- cbn - 1, 0, pb) + min( cbn, 0, 1 - pb)

pb = 1
cb = 2
db = -2
dbn = -1  !!!!! right answer !!!
cbn = 0

1 rl) dbn = -2 - max(- 0 - 1, 0, 1) - min(0, 0, 1 - 1) = - 2 - 1 - 0 = -3   wrong!

2 rr) dbn = -2 + max(- 0 - 1, 0, 1) + min(0, 0, 1 - 1) = -2 + 1 + 0 = -1    !!! that s right!


/*******************************************************************************
                    right rotation  current is right

                     dad (db)                       dad (dbn)
                    /        \                   /           \
                   /          \                 /             \
                  /\       current (cb)        /\           pivot (pbn)
                 /dl\     /          /\       /dl\           /\       \
                /____\   /          /cr\     /____\         /pl\       \
                     pivot (pb)    /____\                  /____\     current (cbn)
                     /\     /\                                        /\        /\
                    /pl\   /pr\                                      /pr\      /cr\
                   /____\ /____\                                    /____\    /____\

cb(n) current balance factor (new)
pb(n) pivot balance factor (new)

cl current left subtree
pl pivot left subtree
pr pivot right subtree

H(...) hight subtree

*******************************************************************************/

db =  H(dl) - max(H(cr) + 1, H(pr) + 2, H(pl) + 2)
dbn = H(dl) - max(H(cr) + 2, H(pr) + 2, H(pl) + 1)

db =  H(dl) - max(H(cr) - 1, H(pr), H(pl)) - 2
dbn = H(dl) - max(H(cr), H(pr), H(pl) - 1) - 2

dbn - db = max(H(cr) - 1, H(pr), H(pl)) - max(H(cr), H(pr), H(pl) - 1)  + H(pr) - H(pr)
dbn - db = max(- cbn - 1, 0, pb) + min( cbn, 0, 1 - pb)
dbn = db + max(- cbn - 1, 0, pb) + min( cbn, 0, 1 - pb)

dbn = db + max(- cbn - 1, 0, pb) + min( cbn, 0, 1 - pb)



/*******************************************************************************
                    left rotation current is left

                    g (gb)                            g (gbn)
                   /     /\                            /     /\
                  /     /gl\                          /     /gl\
                 /     /____\                        /     /____\
             dad (db)                            dad (dbn)
           /        \                          /         \
          /          \                        /           \
    current (cb)     /\                    pivot (pbn)    /\
   /\     \         /dl\                 /         /\    /dl\
  /cl\     \       /____\               /         /pr\  /____\
 /____\    pivot (pb)              current (cbn) /____\
           /\     /\                /\      /\
          /pl\   /pr\              /cl\    /pl\
         /____\ /____\            /____\  /____\


db(n) dad balance factor (new)
cb(n) current balance factor (new)
pb(n) pivot balance factor (new)

dl dad left subtree
cl current left subtree
pl pivot left subtree
pr pivot right subtree

H(...) hight subtree

*******************************************************************************/

db =  max(H(cl)+ 1, H(pl) + 2, H(pr) + 2) - H(dl)
dbn = max(H(cl)+ 2, H(pl) + 2, H(pr) + 1) - H(dl)

db =  max(H(cl) - 1, H(pl), H(pr)) - H(dl) + 2
dbn = max(H(cl), H(pl), H(pr) - 1) - H(dl) + 2
dbn - db = max(H(cl), H(pl), H(pr) - 1) - max(H(cl) - 1, H(pl), H(pr))
dbn - db = max(H(cl), H(pl), H(pr) - 1) + min(-H(cl) + 1, -H(pl), -H(pr)) -H(pl) +H(pl)
dbn - db = max(H(cl)-H(pl), H(pl)-H(pl), H(pr)-H(pl) - 1) + min(+H(pl)-H(cl) + 1, +H(pl)-H(pl), +H(pl)-H(pr))
dbn - db = max(cbn, 0, - pb - 1) + min(1 - cbn, 0, pb)
dbn = db + max(cbn, 0, - pb - 1) + min(1 - cbn, 0, pb)

gbn = max(H(cl) + 3, H(pl) + 3, H(pr) + 2, H(dl) + 1)  - H(gl)
gb =  max(H(cl) + 2, H(pl) + 3, H(pr) + 3, H(dl) + 1)  - H(gl)

gbn = gb + delta

delta = max(H(cl) + 3, H(pl) + 3, H(pr) + 2, H(dl) + 1) - max(H(cl) + 2, H(pl) + 3, H(pr) + 3, H(dl) + 1)
max(
    max(1, cbn, H(cl) - H(pr), H(cl) - H(dl) + 2),
    ...
)

/*******************************************************************************/

-- right rotation
delta = max(- cbn - 1, 0, pb) + min(cbn, 0, 1 - pb)

= max(- cbn - 1, 0, pb) - max(-cbn, 0, pb - 1)
= max(-cbn-1, -cbn-pb, cbn, pb-1, pb+cbn, pb, 1)
-- current left
dbn = db - max(- cbn - 1, 0, pb) - min(cbn, 0, 1 - pb)
dbn = db - delta
-- current right
dbn = db + max(- cbn - 1, 0, pb) + min(cbn, 0, 1 - pb)
dbn = db + delta

--left rotation
delta = max(cbn - 1, 0, -pb) + min(-cbn, 0, 1 + pb)

=max(cbn - 1, 0, -pb) - max(cbn, 0, - 1 - pb)
=max(cbn-1, cbn+pb, -cbn, pb+1, -pb-cbn, -pb, 1)
-- current left
dbn = db + max(cbn, 0, - pb - 1) + min(1 - cbn, 0, pb)
dbn = db - min(-cbn, 0, pb + 1) - max(cbn - 1, 0, -pb)
dbn = db - max(cbn - 1, 0, -pb) - min(-cbn, 0, 1 + pb)
dbn = db - delta
-- current right
dbn = db + max(cbn - 1, 0, -pb) + min(-cbn, 0, 1 + pb)
dbn = db + delta




/*******************************************************************************
                    left balance change

                  g (gb)                            g (gbn)
                 /                                 /
                /                                 /
             d (db)                            d (dbn)
           /        \                        /        \
          /          \                      /          \
       l (lb)        r (rb)              l (lbn)        r (rbn)
     /\     /\       /\    /\          /\     /\       / \    / \
    /ll\   /lr\     /rl\  /rr\        /  \   /  \     /rln\  /rrn\
   /____\ /____\   /____\/____\      / lln\ / lrn\   /_____\/_____\


d dad
l left
r rigth
node

db(n) dad balance factor (new)
lb(n) left balance factor (new)
rb(n) right balance factor (new)

ll(n) left left subtree (new)
lr(n) left right subtree (new)
rl(n) right left subtree (new)
rr(n) right right subtree (new)

H(...) hight subtree

*******************************************************************************/

lb = H(ll)-H(lr)
lbn = H(lln)-H(lr) | H(ll)-H(lrn)
rb = H(rl)-H(rr)
db = max(H(ll), H(lr)) + 1 - (max(H(rl), H(rr)) + 1)
db = max(H(ll), H(lr)) - max(H(rl), H(rr))
db = max(H(ll) - max(H(rl), H(rr)), H(lr) - max(H(rl), H(rr)))
db = max(H(ll) + min(-H(rl), -H(rr)), H(lr) + min(-H(rl), -H(rr)))

db = max(min(H(ll) - H(rl), H(ll) - H(rr)), min(H(lr) - H(rl), H(lr) - H(rr)))
dbn = max(min(H(lln) - H(rl), H(lln) - H(rr)), min(H(lr) - H(rl), H(lr) - H(rr)))


db =        max(H(ll), H(lr)) - max(H(rl), H(rr))
dbn =       max(H(lln), H(lr)) - max(H(rl), H(rr)) | max(H(ll), H(lrn)) - max(H(rl), H(rr))
        |   max(H(ll), H(lr)) - max(H(rln), H(rr)) | max(H(ll), H(lr)) - max(H(rl), H(rrn))

1)
    dbn - db = max(H(lln), H(lr)) -  max(H(ll), H(lr))
    dbn - db = max(H(lln), H(lr)) +  min(-H(ll), -H(lr))
    dbn - db = max(H(lln) + min(-H(ll), -H(lr)), H(lr) + min(-H(ll), -H(lr)))
    dbn - db = max(min(H(lln)-H(ll), H(lln)-H(lr)), min(H(lr)-H(ll), H(lr)-H(lr)))
    dbn - db = max(min(H(lln)-H(ll), lbn), min(-lb, 0))
    dbn = db + max(min( H(lln)-H(ll) , lbn), min(-lb, 0))       !!

2)
    dbn - db = max(H(ll), H(lrn)) -  max(H(ll), H(lr))
    dbn - db = max(H(ll), H(lrn)) +  min(-H(ll), -H(lr))
    dbn - db = max(H(ll) + min(-H(ll), -H(lr)), H(lrn) + min(-H(ll), -H(lr)))
    dbn - db = max(min(H(ll)-H(ll), H(ll)-H(lr)), min(H(lrn)-H(ll), H(lrn)-H(lr)))
    dbn - db = max(min(0, lb), min(-lbn, H(lrn)-H(lr)))
    dbn = db + max(min( H(lrn)-H(lr), -lbn), min(lb, 0))        !!

3)
    dbn - db = max(H(rln), H(rr)) -  max(H(rl), H(rr))
    dbn - db = max(H(rln), H(rr)) +  max(-H(rl), -H(rr))
    dbn - db = max(H(rln) + min(-H(rl), -H(rr)), H(rr) + min(-H(rl), -H(rr)))
    dbn - db = max(min(H(rln)-H(rl), H(rln)-H(rr)), min(H(rr)-H(rl), H(rr)-H(rr)))
    dbn = db + max(min( H(rln)-H(rl), rbn), min(-rb, 0))        !!

4)
    dbn = db + max(min( H(rrn)-H(rr), -rbn), min(rb, 0))        !!

total:
    dbn = db + max(min( H(lln)-H(ll), lbn), min(-lb, 0))       !!
    dbn = db + max(min( H(rln)-H(rl), rbn), min(-rb, 0))       !!
    dbn = db + max(min( H(lrn)-H(lr), -lbn), min(lb, 0))       !!
    dbn = db + max(min( H(rrn)-H(rr), -rbn), min(rb, 0))       !!


    gbn = gb + max(min( deltaH.son.left, dbn), min(-db, 0))
    gbn = gb + max(min( deltaH.son.right, -dbn), min(db, 0))