package com.example.catastrophecompass.UILayer.HQOrganizer.Admin;

java
        Copy code
public class OrganizationAdapter extends RecyclerView.Adapter<OrganizationAdapter.OrganizationViewHolder> {

    private List<Organization> organizationList;
    private Context context;

    public OrganizationAdapter(Context context, List<Organization> organizationList) {
        this.context = context;
        this.organizationList = organizationList;
    }

    @NonNull
    @Override
    public OrganizationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_organization, parent, false);
        return new OrganizationViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrganizationViewHolder holder, int position) {
        Organization organization = organizationList.get(position);
        holder.organizationName.setText(organization.getName());

        holder.editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, AddEditOrganizationActivity.class);
                intent.putExtra("Organization", organization);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return organizationList.size();
    }

    public static class OrganizationViewHolder extends RecyclerView.ViewHolder {
        TextView organizationName;
        Button editButton;

        public OrganizationViewHolder(@NonNull View itemView) {
            super(itemView);
            organizationName = itemView.findViewById(R.id.organization_name);
            editButton = itemView.findViewById(R.id.edit_organization_button);
        }
    }
}