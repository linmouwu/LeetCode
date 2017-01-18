import java.util.*;

public class Twitter {

    private static class Tweet {
        int userId;
        int tweetId;
        int order;

        Tweet(int userId, int tweetId, int order) {
            this.userId = userId;
            this.tweetId = tweetId;
            this.order = order;
        }
    }

    private int order;
    private PriorityQueue<Tweet> pool;
    private HashMap<Integer, HashSet<Integer>> followships;


    /**
     * Initialize your data structure here.
     */
    public Twitter() {
        order = 0;
        followships = new HashMap<>();
        pool = new PriorityQueue<>(new Comparator<Tweet>() {
            @Override
            public int compare(Tweet t1, Tweet t2) {
                return t2.order - t1.order;
            }

        });
    }


    /**
     * Compose a new tweet.
     */
    public void postTweet(int userId, int tweetId) {
        Tweet tweet = new Tweet(userId, tweetId, ++order);
        pool.offer(tweet);
    }

    /**
     * Retrieve the 10 most recent tweet ids in the user's news feed. Each item in the news feed must be posted by users who the user followed or by the user herself. Tweets must be ordered from most recent to least recent.
     */
    public List<Integer> getNewsFeed(int userId) {
        HashSet<Integer> users = new HashSet<>();
        users.add(userId);
        if (followships.containsKey(userId)) {
            users.addAll(followships.get(userId));
        }

        List<Tweet> tmp = new ArrayList<>();
        List<Integer> result = new ArrayList<>();

        while (result.size() < 10 && !pool.isEmpty()) {
            Tweet next = pool.poll();
            tmp.add(next);
            if (users.contains(next.userId)) {
                result.add(next.tweetId);
            }
        }
        pool.addAll(tmp);

        return result;
    }

    /**
     * Follower follows a followee. If the operation is invalid, it should be a no-op.
     */
    public void follow(int followerId, int followeeId) {
        if (!followships.containsKey(followerId)) {
            followships.put(followerId, new HashSet<>());
        }
        if (!followships.get(followerId).contains(followeeId)) {
            followships.get(followerId).add(followeeId);
        }
    }

    /**
     * Follower unfollows a followee. If the operation is invalid, it should be a no-op.
     */
    public void unfollow(int followerId, int followeeId) {
        if (followships.containsKey(followerId)) {
            if (followships.get(followerId).contains(followeeId)) {
                followships.get(followerId).remove(followeeId);
            }
        }
    }
}

/**
 * Your Twitter object will be instantiated and called as such:
 * Twitter obj = new Twitter();
 * obj.postTweet(userId,tweetId);
 * List<Integer> param_2 = obj.getNewsFeed(userId);
 * obj.follow(followerId,followeeId);
 * obj.unfollow(followerId,followeeId);
 */