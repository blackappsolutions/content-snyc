
/**
 * Interface values for ResourceBundle "ContentJobContentTypes".
 * @see ContentJobContentTypes_properties#INSTANCE
 */
interface ContentJobContentTypes_properties {

/**
 *ContentJob
 */
  ContentJob_text: string;
  "ContentJob_localSettings.sourceContent_text": string;
  "ContentJob_localSettings.sourceContentPaths_text": string;
  "ContentJob_localSettings.recursive_true_text": string;
  "ContentJob_localSettings.recursive_tooltip": string;
  "ContentJob_localSettings.job-type_text": string;
  "ContentJob_localSettings.run-job-every_text": string;
  "ContentJob_localSettings.export-storage-url_text": string;
  "ContentJob_localSettings.export-storage-url_tooltip": string;
  "ContentJob_localSettings.zip-directory_text": string;
  "ContentJob_localSettings.zip-directory_tooltip": string;
  "ContentJob_localSettings.start-at_text": string;
  "ContentJob_localSettings.start-at_tooltip": string;
  ContentJob_active_text: string;
  ContentJob_retries_text: string;
  ContentJob_retries_tooltip: string;
  ContentJob_logOutput_text: string;
  ContentJob_lastRun_text: string;
  ContentJob_lastRunSuccessful_text: string;
}

/**
 * Singleton for the current user Locale's instance of ResourceBundle "ContentJobContentTypes".
 * @see ContentJobContentTypes_properties
 */
const ContentJobContentTypes_properties: ContentJobContentTypes_properties = {
  ContentJob_text: "ContentJob",
  "ContentJob_localSettings.sourceContent_text": "The folder/content which should be synced",
  "ContentJob_localSettings.sourceContentPaths_text": "If not given above, you can specify content paths line by line here",
  "ContentJob_localSettings.recursive_true_text": "Sync recursive",
  "ContentJob_localSettings.recursive_tooltip": "",
  "ContentJob_localSettings.job-type_text": "Job type",
  "ContentJob_localSettings.run-job-every_text": "Repeat every (optional)",
  "ContentJob_localSettings.export-storage-url_text": "Storage-URL",
  "ContentJob_localSettings.export-storage-url_tooltip": "file:/// | s3:// | http(s)://user:pass@host/rest_put_path",
  "ContentJob_localSettings.zip-directory_text": "Directory inside ZIP (optional)",
  "ContentJob_localSettings.zip-directory_tooltip": "You can define from which directory inside the ZIP the import starts",
  "ContentJob_localSettings.start-at_text": "Time, this ContentJob should run",
  "ContentJob_localSettings.start-at_tooltip": "(under development - experimental feature)",
  ContentJob_active_text: "Activates this ContentJob",
  ContentJob_retries_text: "Number of re-tries until we give up",
  ContentJob_retries_tooltip: "(under development)",
  ContentJob_logOutput_text: "Execution protocol",
  ContentJob_lastRun_text: "Last run was at",
  ContentJob_lastRunSuccessful_text: "Last run was successful? (1=true|0=false)",
};

export default ContentJobContentTypes_properties;
