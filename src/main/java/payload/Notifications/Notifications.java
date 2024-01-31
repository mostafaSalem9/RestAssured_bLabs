package payload.Notifications;

public class Notifications {
    private String channel;
    private int priority;
    private String recipient_list;
    private String template_id;
    private TemplateParameters template_parameters;

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public String getRecipient_list() {
        return recipient_list;
    }

    public void setRecipient_list(String recipient_list) {
        this.recipient_list = recipient_list;
    }

    public String getTemplate_id() {
        return template_id;
    }

    public void setTemplate_id(String template_id) {
        this.template_id = template_id;
    }

    public TemplateParameters getTemplate_parameters() {
        return template_parameters;
    }

    public void setTemplate_parameters(TemplateParameters template_parameters) {
        this.template_parameters = template_parameters;
    }
}
