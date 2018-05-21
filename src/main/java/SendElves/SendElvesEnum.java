package SendElves;

public enum SendElvesEnum {
     SYNC_CREATERT_URI("/api/v2/rt/exec"),
     SYNC_QUEUE_RESULT("/api/v2/queue/result"),
     SYNC_CREATEQUEUE_URI("/api/v2/queue/create"),
     SYNC_ADDTASK_URI("/api/v2/queue/addtask"),
     ELVES_COMMIT_URI("/api/v2/queue/commit"),
     ELVES_RESULT_URI("/api/v2/queue/result"),
     ELVES_CREATEQKSQUEUE_URI("/api/v2/queue/qksqueue");

    private String value;
    SendElvesEnum(String value){
        this.value = value;
    }
}
