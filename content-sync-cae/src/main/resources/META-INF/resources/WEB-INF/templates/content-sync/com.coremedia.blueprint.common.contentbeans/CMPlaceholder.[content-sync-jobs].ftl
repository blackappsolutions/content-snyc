<#-- @ftlvariable name="contentSyncJobs" type="de.bas.contentsync.cae.ContentSyncJobJanitor" -->

<h3>Scheduled Jobs</h3>
!!! Currently Under Development !!!
<p>
	If the extension content-sync is used and jobs are scheduled, you can see them below:
</p>
<ul>
    <#list contentSyncJobs.taskList![] as scheduledFuture>
			<#--li>Seconds until start: ${scheduledFuture.getDelay(3)}</li-->
    </#list>
</ul>
