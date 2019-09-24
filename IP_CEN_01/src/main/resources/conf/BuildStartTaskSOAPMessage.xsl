<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
    xmlns:m="http://types.ws.ucaautomation.hp.com/" 
    xmlns:actions="http://server.action.mediation.uca.hp.com/" >
  <xsl:output indent="yes" method="xml"/>
	<xsl:param name="taskName"/>
	<xsl:param name="originator"/>
	<xsl:param name="openLoop"/>
	<xsl:param name="mode"/>
	<xsl:param name="actionId"/>
	<xsl:param name="actionName"/>
	<xsl:param name="actionType"/>
	<xsl:param name="operation"/>
	<xsl:param name="taskId"/>
	<xsl:param name="originatorId"/>
	<xsl:param name="problem"/>
	<xsl:param name="serviceTypeID"/>
	<xsl:param name="serviceInstanceID"/>
	<xsl:param name="resourceTypeID"/>
	<xsl:param name="resourceInstanceID"/>
	<xsl:param name="dispatchType"/>
	<xsl:param name="dispatchTypeInstance"/>
	<xsl:param name="parameters"/>
	<xsl:template match="/">
				<m:startTask>
					<m:taskName><xsl:value-of select="$taskName"/></m:taskName>
					<m:taskRequestMessage>
						<m:header>
							<m:TaskRequest Originator="{$originator}" OpenLoop="{$openLoop}" Mode="{$mode}">
								<m:ActionId><xsl:value-of select="$actionId"/></m:ActionId>
								<m:ActionName><xsl:value-of select="$actionName"/></m:ActionName>
								<m:ActionType><xsl:value-of select="$actionType"/></m:ActionType>
								<m:Operation><xsl:value-of select="$operation"/></m:Operation>
								<m:TaskId><xsl:value-of select="$taskId"/></m:TaskId>
								<m:OriginatorId><xsl:value-of select="$originatorId"/></m:OriginatorId>
								<m:Problem><xsl:value-of select="$problem"/></m:Problem>
							</m:TaskRequest>
						</m:header>
						<m:body>
							<m:Parameters>
							<xsl:value-of disable-output-escaping="yes" select="$parameters"/>
							</m:Parameters>
							<m:Service>
								<m:serviceTypeID><xsl:value-of select="$serviceTypeID"/></m:serviceTypeID>
								<m:serviceInstanceID><xsl:value-of select="$serviceInstanceID"/></m:serviceInstanceID>
							</m:Service>
							<m:Resource>
								<m:resourceTypeID><xsl:value-of select="$resourceTypeID"/></m:resourceTypeID>
								<m:resourceInstanceID><xsl:value-of select="$resourceInstanceID"/></m:resourceInstanceID>
							</m:Resource>
						</m:body>
					</m:taskRequestMessage>
					<m:dispatchType><xsl:value-of select="$dispatchType"/></m:dispatchType>
					<m:dispatchTypeInstance><xsl:value-of select="$dispatchTypeInstance"/></m:dispatchTypeInstance>
				</m:startTask>
	</xsl:template>
</xsl:stylesheet>
