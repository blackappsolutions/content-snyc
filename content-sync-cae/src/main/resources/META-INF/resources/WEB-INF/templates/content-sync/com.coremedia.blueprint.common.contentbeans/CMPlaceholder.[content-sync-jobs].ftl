<#-- @ftlvariable name="contentSyncJobs" type="de.bas.contentsync.engine.ContentSyncJobJanitor" -->
<h3>Scheduled Jobs</h3>
<style>
    table, th, td {
        border: solid;
        border-collapse: collapse;
        padding: .3em .5em;
    }
</style>
<table>
	<thead>
	<tr>
		<td>ID</td>
		<td>Time</td>
	</tr>
	</thead>
	<tbody>
  <#list contentSyncJobs.taskList![] as scheduledFutureHolder>
		<tr>
			<td>
				<a href="/blueprint/servlet/dynamic/content-sync-jobs/terminate/${scheduledFutureHolder.contentSyncJob.contentSync.contentId}?origUrl=${springMacroRequestContext.requestUri}">
					Terminate ${scheduledFutureHolder.contentSync.contentId}
				</a>
			</td>
			<td>${scheduledFutureHolder.contentSync.localSettings.getDate("start-at").getTime()?datetime}</td>
		</tr>
  </#list>
	</tbody>
</table>
