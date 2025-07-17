import { registerAs } from "@nestjs/config";

export default registerAs('gateway_external_api', () => ({
    auth_backend_endpoint: "/ext/auth",
    libraries_backend_endpoint: "/ext/libraries",
    releases_backend_endpoint: "/ext/releases",
    components_backend_endpoint: "/ext/core_component",
    components_children_backend_endpoint: "/ext/core_component/children",
    components_xsd_backend_endpoint: "/ext/core_component/export/standalone",
    bie_backend_endpoint: "/ext/bie_list",
    bie_xsd_backend_endpoint: "/ext/bie/generate",
    bie_packages_backend_endpoint: "/ext/bie_packages",
    component_metadata_page_size: -1,
    bie_metadata_page_size: -1,
}));
