package org.learn;

/**
 * You are given the heads of two sorted linked lists list1 and list2.
 * Merge the two lists into one sorted list. The list should be made by splicing together the nodes of the first two lists.
 * Return the head of the merged linked list.
 *
 * Example 1:
 *
 * Input: list1 = [1,2,4], list2 = [1,3,4]
 * Output: [1,1,2,3,4,4]
 *
 * Example 2:
 *
 * Input: list1 = [], list2 = []
 * Output: []
 */
class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}

public class MergeTwoSortedLists {

    private static ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode list3 = new ListNode(0);
        ListNode dummy_head = list3;

        while(list1 != null || list2 != null){
            if(list1 != null && list2 != null){
                if(list1.val < list2.val){
                    list3.next = new ListNode(list1.val);
                    list1 = list1.next;
                    list3 = list3.next;
                }
                else if (list1.val > list2.val){
                    list3.next = new ListNode(list2.val);
                    list2 = list2.next;
                    list3 = list3.next;
                }
                else {
                    list3.next = new ListNode(list2.val);
                    list3.next.next = new ListNode(list2.val);
                    list1 = list1.next;
                    list2 = list2.next;
                    list3 = list3.next.next;
                }
            }
            else {
                if(list1 == null && list2 != null){
                    list3.next = new ListNode(list2.val);
                    list3 = list3.next;
                    list2 = list2.next;
                }
                else if(list1 != null && list2 == null){
                    list3.next = new ListNode(list1.val);
                    list3 = list3.next;
                    list1 = list1.next;
                }
            }
        }

        return dummy_head.next;
    }

    public static void main(String[] args) {
        ListNode list13 = new ListNode(4, null);
        ListNode list12 = new ListNode(2, list13);
        ListNode list1 = new ListNode(1, list12);

        ListNode list23 = new ListNode(4, null);
        ListNode list22 = new ListNode(3, list23);
        ListNode list2 = new ListNode(1, list22);

        ListNode list3 = mergeTwoLists(list1, list2);
        System.out.println("CC");
        while(list1 != null) {

        }
    }
}
